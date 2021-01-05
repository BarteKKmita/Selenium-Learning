package com.learning.pages.google;

import static org.testng.Assert.assertEquals;

import com.google.inject.Inject;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;


@Guice(modules = DriverModule.class)
public class FirstSeleniumTest {

  @Inject
  private PropertiesReader propertiesReader;
  @Inject
  private BrowserDriver browserDriver;

  private WebDriver driver;
  private GoogleSearchPage googleSearchPage;

  @BeforeTest
  public void setUp() {
    driver = browserDriver.getDriver();
    googleSearchPage = new GoogleSearchPage(driver, propertiesReader);
  }

  @Test
  void shouldGoToJunit5WebPage() {
    driver.get("https://junit.org/junit5/docs/current/user-guide/");
  }

  @lombok.SneakyThrows
  @Test
  void shouldFindElementOnGoogleWebSite() {
    getGoogleWebsite();
    WebElement googleSearch = googleSearchPage.getGoogleSearchTextBox(driver);
    WebElement searchButton = googleSearchPage.getSearchInGoogleBtn(driver);
    googleSearch.sendKeys("Getting grip on it! ");
    searchButton.submit();
  }

  @Test
  void shouldFind9InputElementsOnGoogle() {
    //Given
    int expectedInputElements = 8;
    //When
    getGoogleWebsite();
    int actualInputElements = driver.findElements(By.xpath("//input")).size();
    //Then
    assertEquals(expectedInputElements, actualInputElements);
  }

  private void getGoogleWebsite() {
    driver.get(googleSearchPage.getGoogleURL());
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }
}
