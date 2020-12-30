package com.learning;

import static com.learning.pages.SwagLabsLoginPage.LOGIN_PAGE_URL;
import static org.testng.Assert.assertEquals;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import com.learning.pages.SwagLabsLoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = DriverModule.class)
public class SwagLabsLoginPageTest {

  @Inject
  @Named("EdgeDriver")
  private BrowserDriver browserDriver;

  private WebDriver driver;
  private SwagLabsLoginPage swagLabsLoginPage;

  @BeforeClass
  void setUp() {
    driver = browserDriver.getDriver();
    swagLabsLoginPage = new SwagLabsLoginPage(driver);
  }

  @Test(dataProvider = "successfulLoginData")
  void shouldGoToShopPageWhenSuccessfulLogin(String username, String password) {
    //Given
    String expectedPageUrl = "https://www.saucedemo.com/inventory.html";
    //When
    driver.get(LOGIN_PAGE_URL);
    swagLabsLoginPage.performLogin(username, password);
    String actualPageUrl = driver.getCurrentUrl();
    //Then
    assertEquals(actualPageUrl, expectedPageUrl);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenUnsuccessfulLogin(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    //When
    driver.get(LOGIN_PAGE_URL);
    swagLabsLoginPage.performLogin(username, password);
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    //Then
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenLackOfPassword(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Password is required";
    //When
    driver.get(LOGIN_PAGE_URL);
    swagLabsLoginPage.performLogin(username, "");
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    //Then
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenLackOfUsername(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Username is required";
    //When
    driver.get(LOGIN_PAGE_URL);
    swagLabsLoginPage.performLogin("", password);
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    //Then
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }


  @DataProvider(name = "successfulLoginData")
  private Object[][] getDataForSuccessfulLogin() {
    return new Object[][]{
        {"standard_user", "secret_sauce"},
        {"problem_user", "secret_sauce"}
    };
  }

  @DataProvider(name = "unsuccessfulLoginData")
  private Object[][] getDataForUnSuccessfulLogin() {
    return new Object[][]{
        {"hacker", "secret_sauce"},
        {"random internet user", "secret_sauce"}
    };
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    driver.quit();
  }
}
