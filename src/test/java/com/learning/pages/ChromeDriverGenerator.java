package com.learning.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverGenerator {

  private final WebDriver driver;
  public static final String CHROME_USER_DATA = "C://Users//Bartek//AppData//Local//Google//Chrome//User Data";

  public ChromeDriverGenerator() {
    driver =new ChromeDriver(getChromeOptions());
  }

  public WebDriver getChromeDriver() {
    return driver;
  }

  private ChromeOptions getChromeOptions(){
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("user-data-dir=" + CHROME_USER_DATA);
    options.addArguments("profile-directory=Profile 1");
    return options;
  }
}
