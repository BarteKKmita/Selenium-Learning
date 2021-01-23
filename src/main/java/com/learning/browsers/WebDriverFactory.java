package com.learning.browsers;

import com.google.common.base.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebDriverFactory implements InitializingBean {

  @Value("${browser}")
  private String browserName;
  private String pathToEdgeDriver;
  private WebDriver webDriver;

  @Autowired
  public void setPathToEdgeDriver(@Value("${pathToEdgeDriver}") String pathToEdgeDriver) {
    this.pathToEdgeDriver = pathToEdgeDriver;
  }

  @Override
  public void afterPropertiesSet() {
    webDriver = generateWebDriver();
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  private WebDriver generateWebDriver() {
    if (browserName.equalsIgnoreCase("Chrome")) {
      return new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("Edge")) {
      System.setProperty("webdriver.edge.driver", getPathToEdgeDriver());
      return new EdgeDriver();
    } else {
      throw new BrowserNotSupportedException("Unknown browser.");
    }
  }

  private String getPathToEdgeDriver() {
    if (Strings.isNullOrEmpty(pathToEdgeDriver)) {
      throw new IllegalStateException("Path to Edge driver cannot be null or empty");
    } else {
      return pathToEdgeDriver;
    }
  }
}
