package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;

import com.google.inject.Inject;
import com.learning.ApplicationModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = ApplicationModule.class)
public class SwagLabsLoginPageTest {

  @Inject
  private SwagLabsLoginPage swagLabsLoginPage;

  @Test(dataProvider = "successfulLoginData")
  void shouldGoToShopPageWhenSuccessfulLogin(String username, String password) {
    //Given
    String expectedPageUrl = "https://www.saucedemo.com/inventory.html";
    //When
    swagLabsLoginPage.openSwagLabsWebsite();
    swagLabsLoginPage.performLogin(username, password);
    String actualPageUrl = swagLabsLoginPage.getCurrentUrl();
    //Then
    assertEquals(actualPageUrl, expectedPageUrl);
  }

  @Test(dataProvider = "unsuccessfulLoginData")
  void shouldErrorMessageBeVisibleWhenUnsuccessfulLogin(String username, String password) {
    //Given
    String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    //When
    swagLabsLoginPage.openSwagLabsWebsite();
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
    swagLabsLoginPage.openSwagLabsWebsite();
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
    swagLabsLoginPage.openSwagLabsWebsite();
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
    swagLabsLoginPage.close();
  }
}
