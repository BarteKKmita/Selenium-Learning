package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase {

  protected final WebDriver driver;

  public PageBase(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public abstract boolean waitForPageToLoad();
}
