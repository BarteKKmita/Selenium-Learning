package com.learning.pages;

import java.io.Closeable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageBase implements Closeable {

  protected final WebDriver driver;

  public PageBase(WebDriver driver) {
    this.driver=driver;
    PageFactory.initElements(driver, this);
  }

  public void close() {
    driver.close();
    driver.quit();
  }

  public abstract boolean waitForPageToLoad();
}
