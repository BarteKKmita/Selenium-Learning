package com.learning.browsers;

import com.learning.configuration.PropertiesReader;
import com.netflix.governator.annotations.AutoBind;
import javax.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {

  private static final String BROWSER_PROPERTIES_NAME = "browser";
  private static final String PATH_TO_EDGE_DRIVER = "pathToEdgeDriver";
  private final PropertiesReader propertiesReader;

  private WebDriver webDriver;

  public WebDriverFactory(PropertiesReader propertiesReader) {
    this.propertiesReader = propertiesReader;
    webDriver = generateWebDriver();
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  private WebDriver generateWebDriver() {
    String browserName = propertiesReader.getProperty(BROWSER_PROPERTIES_NAME);
    if (browserName.equalsIgnoreCase("Chrome")) {
      return new ChromeDriver();
    } else if (browserName.equalsIgnoreCase("Edge")) {
      System.setProperty("webdriver.edge.driver", propertiesReader.getProperty(PATH_TO_EDGE_DRIVER));
      return new EdgeDriver();
    } else {
      throw new BrowserNotSupportedException("Unknown browser.");
    }
  }

  @PreDestroy
  public void closeBrowser(){
    webDriver.close();
    webDriver.quit();
  }
}

