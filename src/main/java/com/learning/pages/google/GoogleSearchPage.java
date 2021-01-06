package com.learning.pages.google;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.PageBase;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

  public void openGoogleWebsite(){
    driver.get(getGoogleURL());
    waitForPageToLoad();
  }

  public void searchGoogle(String searchPhrase) {
    searchBox.sendKeys(searchPhrase);
    searchButton.submit();
  }

  public WebElement getGoogleSearchTextBox() {
    return searchBox;
  }

  public WebElement getSearchInGoogleBtn() {
    return searchButton;
  }

  @Override
  public boolean waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(ExpectedConditions
        .visibilityOfAllElements(searchBox,searchButton))
        .stream()
        .allMatch(Objects::nonNull);
  }

  public int getAllInputsCount() {
    return driver.findElements(By.xpath("//input")).size();
  }

  private String getGoogleURL() {
    return propertiesReader.getProperty(GOOGLE_URL_PROPERTIES_NAME);
  }
}
