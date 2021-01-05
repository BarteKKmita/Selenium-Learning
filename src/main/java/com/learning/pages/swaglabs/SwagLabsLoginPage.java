package com.learning.pages.swaglabs;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwagLabsLoginPage extends PageBase {

  private final PropertiesReader propertiesReader;
  private static final String SWAGLABS_URL_PROPERTIES_NAME = "swagLabsURL";

  @FindBy(id = "user-name")
  private WebElement usernameTextBox;

  @FindBy(id = "password")
  private WebElement passwordTextBox;

  @FindBy(tagName = "h3")
  private WebElement errorButton;

  @FindBy(css = "input#login-button")
  private WebElement loginButton;

  public SwagLabsLoginPage(WebDriver driver, PropertiesReader propertiesReader) {
    super(driver);
    this.propertiesReader = propertiesReader;
  }

  public String getSwagLabsURL() {
    return propertiesReader.getProperty(SWAGLABS_URL_PROPERTIES_NAME);
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
  public void waitForPageToLoad(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions
        .visibilityOfAllElements(usernameTextBox, passwordTextBox,
            loginButton));
  }
}
