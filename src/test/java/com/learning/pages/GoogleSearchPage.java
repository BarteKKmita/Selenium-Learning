package com.learning.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {

  public static final String GOOGLE_URL = "https://google.com";

  public static WebElement getGoogleSearchTextBox(WebDriver driver) {
    return driver.findElement(By.name("q"));
  }

  public static WebElement getSearchInGoogleBtn(WebDriver driver) {
    return driver.findElement(By.name("btnK"));
  }

}
