package com.learning.pages;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = DriverModule.class)
public class FluentWaitTest {


  @Inject
  @Named("EdgeDriver")
  private BrowserDriver browserDriver;

  private WebDriver driver;

  @BeforeSuite
  void setUp() {
    driver = browserDriver.getDriver();
  }


  @Test
  void shouldNotBeNullWhenInjected() {
    GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);
    driver.get(GoogleSearchPage.GOOGLE_URL);
    googleSearchPage.searchGoogle("Simodrive");
    new FluentWait<>(driver)
        .withTimeout(Duration.ofMillis(1000))
        .pollingEvery(Duration.ofMillis(100));

  }


  @AfterSuite(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }

}
