package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

import com.learning.TestsConfiguration;
import com.learning.browsers.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(classes = TestsConfiguration.class)
public class SwagLabsLoginPageTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private Browser browser;
  @Autowired
  private SwagLabsLoginPage swagLabsLoginPage;
  @Autowired
  private SwagLabsInventoryPage swagLabsInventoryPage;

  @Test(dataProvider = "successfulLoginData")
  void shouldGoToShopPageWhenSuccessfulLogin(String username, String password) {
    //Given
    String expectedPageUrl = swagLabsInventoryPage.getURL();
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    //Then
    String actualPageUrl = browser.getCurrentPageURL();
    assertEquals(actualPageUrl, expectedPageUrl);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenUnsuccessfulLogin(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    //Then
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test
  void shouldErrorMessageBeVisibleWhenLackOfPassword() {
    //Given
    String username = "standard_user";
    String expectedErrorMessage = "Epic sadface: Password is required";
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, "");
    //Then
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test
  void shouldErrorMessageBeVisibleWhenLackOfUsername() {
    //Given
    String password = "secret_sauce";
    String expectedErrorMessage = "Epic sadface: Username is required";
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin("", password);
    //Then
    String actualErrorMessage = swagLabsLoginPage.getErrorText();
    assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test
  void shouldFailToLoginWhenLackOfCredentials() {
    //Given
    String expectedURL = swagLabsLoginPage.getPageURL();
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin("", "");
    //Then
    assertEquals(browser.getCurrentPageURL(), expectedURL);
  }

  @Test
  void shouldNotGoToInventoryPageWithoutLogin() {
    //Given
    String expectedURL = swagLabsLoginPage.getPageURL();
    //When
    swagLabsInventoryPage.open();
    //Then
    assertEquals(browser.getCurrentPageURL(), expectedURL);
  }

  @Test(dataProvider = "lockedUserData")
  void shouldNotLoginWhenLockedUser(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    //Then
    assertEquals(swagLabsLoginPage.getErrorText(), expectedErrorMessage);
  }

  @Test
  void shouldFailToLoadPageWithProblemUserLogin() {
    //Given
    String username = "problem_user";
    String password = "secret_sauce";
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    assertThrows(() -> browser.waitForPageToLoad(swagLabsInventoryPage));
  }

  @DataProvider(name = "successfulLoginData")
  private Object[][] getDataForSuccessfulLogin() {
    return new Object[][]{
        {"standard_user", "secret_sauce"},
    };
  }

  @DataProvider(name = "lockedUserData")
  private Object[][] getDataForSuccessfulLockedUser() {
    return new Object[][]{
        {"locked_out_user", "secret_sauce"},
    };
  }

  @DataProvider(name = "unsuccessfulLoginData")
  private Object[][] getDataForUnSuccessfulLogin() {
    return new Object[][]{
        {"hacker", "secret_sauce"},
        {"standard_user", "hackers_password"}
    };
  }
}
