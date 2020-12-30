package com.learning.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverGenerator implements BrowserDriver {

  private final WebDriver driver;

  public ChromeDriverGenerator() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    driver = new ChromeDriver();
  }

  public WebDriver getDriver() {
    return driver;
  }
}
