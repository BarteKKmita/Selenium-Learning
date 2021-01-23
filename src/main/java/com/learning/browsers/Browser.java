package com.learning.browsers;

import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Browser implements DisposableBean {

  private WebDriver webDriver;

  public Browser(@Autowired WebDriverFactory webDriverFactory) {
    this.webDriver = webDriverFactory.getWebDriver();
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public String getCurrentPageURL() {
    return webDriver.getCurrentUrl();
  }

  public void open(String websiteURL) {
    webDriver.get(websiteURL);
    waitForPageToLoad();
  }

  private void waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    wait.until(isJavascriptReady());
    wait.until(isJQueryReady());
  }

  private Function<WebDriver, Boolean> isJavascriptReady() {
    return driver -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState")
        .equals("complete");
  }

  private Function<WebDriver, Boolean> isJQueryReady() {
    return driver -> {
      try {
        return ((Long) ((JavascriptExecutor) driver)
            .executeScript("return jQuery.active") == 0);
      } catch (Exception e) {
        return true;
      }
    };
  }

  @Override
  public void destroy() {
    webDriver.quit();
  }
}
