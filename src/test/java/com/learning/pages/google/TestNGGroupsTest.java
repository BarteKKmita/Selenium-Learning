package com.learning.pages.google;

import com.google.inject.Inject;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Guice(modules = DriverModule.class)
public class TestNGGroupsTest {

  @Inject
  private BrowserDriver browserDriver;

  @Inject
  private PropertiesReader propertiesReader;

  private WebDriver driver;
  private GoogleSearchPage googleSearchPage;


  @BeforeSuite
  void setUp() {
    driver = browserDriver.getDriver();
    googleSearchPage = new GoogleSearchPage(driver, propertiesReader);
  }

  @Test(groups = {"sanity"})
  void shouldGoToGoogle() {
    driver.get(googleSearchPage.getGoogleURL());
  }

  @Test(priority = 1)
  void test2() {
    System.out.println("This is test 2");
  }

  @Test(groups = {"sanity"})
  void test3() {
    System.out.println("This is test 3");
  }

  @Test(groups = {"smoke"})
  void shouldGoToPaniodprogramowania() {
    driver.get("https://paniodprogramowania.pl/");
    System.out.println("Looking at paniodprogramowania.");
  }

  @Test(groups = {"sanity"})
  @Parameters({"MyParameters"})
  void shouldGetPreparedParameters(@Optional String parameter) {
    System.out.println(parameter);
  }

  @Test(dependsOnGroups = {"sanity"})
  void shouldRunSanitysGroupTestsFirst() {
    System.out.println("I am running lastly");
  }

  @AfterSuite(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }
}