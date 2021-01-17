package com.learning.pages.swaglabs;

import com.learning.browsers.Browser;
import com.learning.pages.PageBase;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    super(browser);
    this.swagLabsURL = swagLabsURL;
  }

  public void open() {
    driver.get(getURL());
    waitForPageToLoad();
  }

  public void performLogin(String username, String password) {
    usernameTextBox.sendKeys(username);
    passwordTextBox.sendKeys(password);
    loginButton.click();
  }

  public String getErrorText() {
    return errorButton.getText();
  }

  @Override
  public boolean waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    return wait.until(ExpectedConditions
        .visibilityOfAllElements(usernameTextBox, passwordTextBox,
            loginButton))
        .stream()
        .allMatch(Objects::nonNull);
  }

  private String getURL() {
    return swagLabsURL;
  }
}
