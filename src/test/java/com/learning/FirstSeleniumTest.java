package com.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstSeleniumTest {

  private static WebDriver driver;
  public static String chromeProfilePath = "C://Users//Bartek//AppData//Local//Google//Chrome/User Data";

  @BeforeAll
  static void setUp() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("user-data-dir=" + chromeProfilePath);
    driver = new ChromeDriver(options);
  }

  @Test
  void shouldGoToJunit5WebPage() {
    driver.get("https://junit.org/junit5/docs/current/user-guide/");
  }

  @lombok.SneakyThrows
  @Test
  void shouldFindElementOnGoogleWebSite() {
    getGoogleWebsite();
    WebElement googleSearch = getGoogleSearchTextBox();
    WebElement searchButton = getSearchInGoogleBtn();
    googleSearch.sendKeys("Getting grip on it! ");
    searchButton.submit();
  }

  @Test
  void shouldFind11InputElementsOnGoogle() {
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

  private WebElement getGoogleSearchTextBox() {
    return driver.findElement(By.name("q"));
  }

  private WebElement getSearchInGoogleBtn() {
    return driver.findElement(By.name("btnK"));
  }

  @AfterEach
  void tearDown() {
    driver.close();
    driver.quit();
  }
}
