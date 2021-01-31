package com.learning.browsers;

import java.util.Arrays;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Browser implements DisposableBean {

  private final WebDriver webDriver;

  public Browser(@Autowired WebDriverFactory webDriverFactory) {
    this.webDriver = webDriverFactory.getWebDriver();
  }

  public DefaultElementLocatorFactory getElementLocator() {
    return new DefaultElementLocatorFactory(webDriver);
  }

  public String getCurrentPageURL() {
    return webDriver.getCurrentUrl();
  }

  public void open(String websiteURL, Loadable page) {
    webDriver.get(websiteURL);
    waitForPageToLoad(page);
  }

  public boolean waitForElementsToLoad(WebElement... elements) {
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    return wait.until(driver -> Arrays.stream(elements)
        .allMatch(WebElement::isEnabled));
  }

  private void waitForPageToLoad(Loadable page) {
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    wait.until(isJavascriptReady());
    page.isLoaded();
  }

  private Function<WebDriver, Boolean> isJavascriptReady() {
    return driver -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState")
        .equals("complete");
  }

  @Override
  public void destroy() {
    webDriver.quit();
  }
}
