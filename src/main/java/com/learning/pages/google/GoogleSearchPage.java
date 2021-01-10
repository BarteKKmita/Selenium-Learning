package com.learning.pages.google;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchPage extends PageBase {

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

  public void open() {
    driver.get(getURL());
    waitForPageToLoad();
  }

  public void search(String searchPhrase) {
    searchBox.sendKeys(searchPhrase);
    searchButton.submit();
  }

  @Override
  public boolean waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(driver -> searchBox.isDisplayed() && searchButton.isEnabled());
  }

  public int getAllInputsCount() {
    return driver.findElements(By.xpath("//input")).size();
  }

  private String getURL() {
    return propertiesReader.getProperty(GOOGLE_URL_PROPERTIES_NAME);
  }
}
