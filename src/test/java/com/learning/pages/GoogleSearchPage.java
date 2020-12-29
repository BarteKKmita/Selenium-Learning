package com.learning.pages;

import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@NoArgsConstructor
public class GoogleSearchPage {

  @FindBy(name = "q")
  private WebElement searchBox;

  @FindBy(name = "btnK")
  private WebElement searchButton;

  public GoogleSearchPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public static final String GOOGLE_URL = "https://google.com";

  public void searchGoogle(String searchPhrase) {
    searchBox.sendKeys(searchPhrase);
    searchButton.submit();
  }
  public static WebElement getGoogleSearchTextBox(WebDriver driver) {
    return driver.findElement(By.name("q"));
  }

  public static WebElement getSearchInGoogleBtn(WebDriver driver) {
    return driver.findElement(By.name("btnK"));
  }
}
