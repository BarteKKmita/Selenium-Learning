package com.learning.pages.google;

import com.google.inject.Inject;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = DriverModule.class)
public class GoogleSearchTest {

  @Inject
  private BrowserDriver driverGenerator;
  @Inject
  private PropertiesReader propertiesReader;

  private GoogleSearchPage googleSearchPage;
  private WebDriver driver;

  @BeforeClass
  public void setUp() {
    driver = driverGenerator.getDriver();
    googleSearchPage = new GoogleSearchPage(driver, propertiesReader);
  }

  @Test
  public void testGoogleSearch() {
    //Given
    driver.get("https://google.com");
    //When
    googleSearchPage.searchGoogle("Simodrive launch");
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }
}
