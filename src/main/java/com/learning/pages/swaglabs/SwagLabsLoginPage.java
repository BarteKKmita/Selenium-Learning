package com.learning.pages.swaglabs;

import com.learning.browsers.Browser;
import com.learning.pages.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwagLabsLoginPage extends PageBase {

  private final String swagLabsURL;

  @FindBy(id = "user-name")
  private WebElement usernameTextBox;
  @FindBy(id = "password")
  private WebElement passwordTextBox;
  @FindBy(tagName = "h3")
  private WebElement errorButton;
  @FindBy(css = "input#login-button")
  private WebElement loginButton;

  public SwagLabsLoginPage(Browser browser, @Value("${swagLabsURL}") String swagLabsURL) {
    this.swagLabsURL = swagLabsURL;
  }

  public void open() {
    browser.open(swagLabsURL);
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
