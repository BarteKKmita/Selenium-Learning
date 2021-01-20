package com.learning.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Browser implements DisposableBean {

  private final String browserName;
  private final String pathToEdgeDriver;
  private WebDriver webDriver;

  public Browser(@Value("${pathToEdgeDriver}") String pathToEdgeDriver, @Value("${browser}") String browserName) {
    this.browserName = browserName;
    this.pathToEdgeDriver = pathToEdgeDriver;
    this.webDriver = generateWebDriver();
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  private WebDriver generateWebDriver() {
    if (browserName.equalsIgnoreCase("Chrome")) {
      return new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("Edge")) {
      System.setProperty("webdriver.edge.driver", pathToEdgeDriver);
      return new EdgeDriver();
    } else {
      throw new BrowserNotSupportedException("Unknown browser.");
    }
  }

  public String getCurrentPageURL() {
    return webDriver.getCurrentUrl();
  }

  @Override
  public void destroy() {
    webDriver.quit();
  }
}
