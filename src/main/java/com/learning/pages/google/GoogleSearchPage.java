package com.learning.pages.google;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage extends BasePage {

  @FindBy(name = "q")
  private WebElement searchBox;

  @FindBy(name = "btnK")
  private WebElement searchButton;

  private final PropertiesReader propertiesReader;
  private static final String GOOGLE_URL_PROPERTIES_NAME = "googleURL";

  public GoogleSearchPage(WebDriver driver, PropertiesReader propertiesReader) {
    super(driver);
    this.propertiesReader = propertiesReader;
  }

  public String getGoogleURL() {
    return propertiesReader.getProperty(GOOGLE_URL_PROPERTIES_NAME);
  }

  public void searchGoogle(String searchPhrase) {
    searchBox.sendKeys(searchPhrase);
    searchButton.submit();
  }

  public WebElement getGoogleSearchTextBox(WebDriver driver) {
    return searchBox;
  }

  public WebElement getSearchInGoogleBtn(WebDriver driver) {
    return searchButton;
  }

  @Override
  public void waitForPageToLoad(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 5);
    wait.until(ExpectedConditions
        .visibilityOfAllElements(searchBox, searchButton));
  }
}
