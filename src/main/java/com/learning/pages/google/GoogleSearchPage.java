package com.learning.pages.google;

import com.learning.pages.PageBase;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleSearchPage extends PageBase {

  private final String googleURL;

  @FindBy(name = "q")
  private WebElement searchBox;
  @FindBy(name = "btnK")
  private WebElement searchButton;
  @FindBy(xpath = "//input")
  private List<WebElement> inputFields;

  public GoogleSearchPage(@Value("${googleURL}") String googleURL) {
    this.googleURL = googleURL;
  }

  public void open() {
    browser.open(googleURL, this);
  }

  public void search(String searchPhrase) {
    searchBox.sendKeys(searchPhrase);
    searchButton.submit();
  }

  public int getAllInputsCount() {
    return inputFields.size();
  }

  @Override
  public boolean isLoaded() {
    return browser.waitForElementsToLoad(searchBox, searchButton);
  }

  private String getURL() {
    return googleURL;
  }
}
