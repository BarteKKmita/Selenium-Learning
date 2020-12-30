package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsLoginPage {

  public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/index.html";

  @FindBy(id = "user-name")
  private WebElement usernameTextBox;

  @FindBy(id = "password")
  private WebElement passwordTextBox;

  @FindBy(tagName = "h3")
  private WebElement errorButton;

  @FindBy(css = "input#login-button")
  private WebElement loginButton;

  public SwagLabsLoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void performLogin(String username, String password) {
    usernameTextBox.sendKeys(username);
    passwordTextBox.sendKeys(password);
    loginButton.click();
  }

  public String getErrorText() {
    return errorButton.getText();
  }
}
