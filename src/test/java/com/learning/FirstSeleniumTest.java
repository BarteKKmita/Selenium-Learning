package com.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstSeleniumTest {

  private WebDriver driver;

  @BeforeAll
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
    WebElement googleSearch = getGoogleSearchTextBox();
    WebElement searchButton = getSearchInGoogleBtn();
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

  private WebElement getGoogleSearchTextBox() {
    return driver.findElement(By.name("q"));
  }

  private WebElement getSearchInGoogleBtn() {
    return driver.findElement(By.name("btnK"));
  }

  @AfterAll
  void shoutDown() {
    driver.close();
    driver.quit();
  }
}
