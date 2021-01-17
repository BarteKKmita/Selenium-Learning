package com.learning.pages;

import com.learning.browsers.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PageBase {

  protected final WebDriver driver;

  public PageBase(@Autowired Browser browser) {
    this.driver = browser.getWebDriver();
    PageFactory.initElements(driver, this);
  }

  public abstract boolean waitForPageToLoad();
}
