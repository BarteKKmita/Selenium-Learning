package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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
    assertTrue(swagLabsInventoryPage.arePicturesLoaded());
    assertEquals(actualPageUrl, expectedPageUrl);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenUnsuccessfulLogin(String username, String password, String expectedErrorMessage) {
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
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

  @Test(dataProvider = "problemUser")
  void shouldFailToLoadPageWithProblemUserLogin(String username, String password) {
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    //Then
    assertFalse(swagLabsInventoryPage.arePicturesLoaded());
  }

  @DataProvider(name = "successfulLoginData")
  private Object[][] getDataForSuccessfulLogin() {
    return new Object[][]{
        {"standard_user", "secret_sauce"},
    };
  }

  @DataProvider(name = "unsuccessfulLoginData")
  private Object[][] getDataForUnSuccessfulLogin() {
    return new Object[][]{
        {"hacker", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
        {"standard_user", "hackers_password", "Epic sadface: Username and password do not match any user in this service"},
        {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
        {"", "secret_sauce", "Epic sadface: Username is required"},
        {"standard_user", "", "Epic sadface: Password is required"}
    };
  }

  @DataProvider(name = "problemUser")
  private Object[][] getDataForProblemUser() {
    return new Object[][]{
        {"problem_user", "secret_sauce"},
    };
  }
}
