package com.learning;

import com.learning.browsers.ChromeDriverGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchTest {
  private WebDriver driver;

  @BeforeClass
  public void setUp() {
    driver = new ChromeDriverGenerator().getDriver();
  }

  @Test
  public void testGoogleSearch() {
    //Given
    driver.get("https://google.com");
    WebElement searchTextBox = driver.findElement(By.name("q"));
    //When
    searchTextBox.sendKeys("Simodrive launch");
    searchTextBox.sendKeys(Keys.ENTER);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
    driver.close();
  }
}
