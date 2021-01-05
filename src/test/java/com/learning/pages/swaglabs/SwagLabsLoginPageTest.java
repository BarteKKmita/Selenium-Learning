package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;

import com.google.inject.Inject;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = DriverModule.class)
public class SwagLabsLoginPageTest {

  @Inject
  private BrowserDriver browserDriver;
  @Inject
  private PropertiesReader propertiesReader;

  private WebDriver driver;
  private SwagLabsLoginPage swagLabsLoginPage;

  @BeforeClass
  void setUp() {
    driver = browserDriver.getDriver();
    swagLabsLoginPage = new SwagLabsLoginPage(driver, propertiesReader);
  }

  @Test(dataProvider = "successfulLoginData")
  void shouldGoToShopPageWhenSuccessfulLogin(String username, String password) {
    //Given
    String expectedPageUrl = "https://www.saucedemo.com/inventory.html";
    //When
    driver.get(swagLabsLoginPage.getSwagLabsURL());
    waitForPageToLoad();
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
    driver.get(swagLabsLoginPage.getSwagLabsURL());
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
    driver.get(swagLabsLoginPage.getSwagLabsURL());
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
    driver.get(swagLabsLoginPage.getSwagLabsURL());
    waitForPageToLoad();
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

  private void waitForPageToLoad() {
    swagLabsLoginPage.waitForPageToLoad(driver);
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    driver.quit();
  }
}
