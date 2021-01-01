package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
  public BasePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public abstract void waitForPageToLoad(WebDriver driver);
}
