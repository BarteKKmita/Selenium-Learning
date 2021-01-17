package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;

import com.learning.browsers.Browser;
import com.learning.browsers.BrowserConfiguration;
import com.learning.pages.PagesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {BrowserConfiguration.class, PagesConfiguration.class})
public class SwagLabsLoginPageTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private Browser browser;
  @Autowired
  private SwagLabsLoginPage swagLabsLoginPage;
  @Autowired
  private SwagLabsInventory swagLabsInventoryPage;

  @Test(dataProvider = "successfulLoginData")
  void shouldGoToShopPageWhenSuccessfulLogin(String username, String password) {
    //Given
    String expectedPageUrl = swagLabsInventoryPage.getURL();
    //When
    swagLabsLoginPage.open();
    swagLabsLoginPage.performLogin(username, password);
    String actualPageUrl = browser.getCurrentPageURL();
    //Then
    assertEquals(actualPageUrl, expectedPageUrl);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenUnsuccessfulLogin(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    //When
    swagLabsLoginPage.open();
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
    swagLabsLoginPage.open();
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
    swagLabsLoginPage.open();
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
}
