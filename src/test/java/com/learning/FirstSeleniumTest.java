package com.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.learning.pages.ChromeDriverGenerator;
import com.learning.pages.GoogleSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

  private WebDriver driver;

  @BeforeTest
  public void setUp() {
    driver = new ChromeDriverGenerator().getChromeDriver();
  }

  @Test
  void shouldGoToJunit5WebPage() {
    driver.get("https://junit.org/junit5/docs/current/user-guide/");
  }

  @lombok.SneakyThrows
  @Test
  void shouldFindElementOnGoogleWebSite() {
    getGoogleWebsite();
    WebElement googleSearch = GoogleSearchPage.getGoogleSearchTextBox(driver);
    WebElement searchButton = GoogleSearchPage.getSearchInGoogleBtn(driver);
    googleSearch.sendKeys("Getting grip on it! ");
    searchButton.submit();
  }

  @Test
  void shouldFind9InputElementsOnGoogle() {
    //Given
    int expectedInputElements = 9;
    //When
    getGoogleWebsite();
    int actualInputElements = driver.findElements(By.xpath("//input")).size();
    //Then
    assertEquals(expectedInputElements, actualInputElements);
  }

  private void getGoogleWebsite() {
    driver.get("https://google.com");
  }

  @AfterClass
  void tearDown() {
    driver.close();
    driver.quit();
  }
}
