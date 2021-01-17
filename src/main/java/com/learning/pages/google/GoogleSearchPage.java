package com.learning.pages.google;

import com.learning.browsers.Browser;
import com.learning.pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleSearchPage extends PageBase {

  private final String googleURL;

  @FindBy(name = "q")
  private WebElement searchBox;
  @FindBy(name = "btnK")
  private WebElement searchButton;

  public GoogleSearchPage(Browser browser, @Value("${googleURL}") String googleURL) {
    super(browser);
    this.googleURL = googleURL;
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
    return googleURL;
  }
}
