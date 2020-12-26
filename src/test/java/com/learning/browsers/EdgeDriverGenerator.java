package com.learning.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverGenerator implements BrowserDriver {

  private final WebDriver driver;

  public EdgeDriverGenerator() {
    setEdgeProperty();
    driver = new EdgeDriver();
  }

  public WebDriver getDriver() {
    return driver;
  }

  private void setEdgeProperty() {
    System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
  }
}
