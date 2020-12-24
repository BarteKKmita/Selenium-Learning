package com.learning;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GoogleSearchTest {
  private WebDriver driver;

  @BeforeAll
  public void setUp() {
    driver = new ChromeDriverGenerator().getChromeDriver();
  }

  @Test
  void testGoogleSearch() {
    //Given
    driver.get("https://google.com");
    WebElement searchTextBox = driver.findElement(By.name("q"));
    //When
    searchTextBox.sendKeys("Simodrive launch");
    searchTextBox.sendKeys(Keys.ENTER);
    //Then
    driver.close();
  }
}
