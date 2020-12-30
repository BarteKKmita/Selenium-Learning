package com.learning;

import static com.learning.pages.GoogleSearchPage.GOOGLE_URL;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
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
  @Named("EdgeDriver")
  private BrowserDriver browserDriver;

  private WebDriver driver;


  @BeforeSuite
  void setUp() {
    driver = browserDriver.getDriver();
  }

  @Test(groups = {"sanity"})
  void shouldGoToGoogle() {
    driver.get(GOOGLE_URL);
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
  void shouldRunSanitysGroupTestsFirst(){
    System.out.println("I am running lastly");
  }

  @AfterSuite(alwaysRun = true)
  void tearDown() {
    driver.close();
    driver.quit();
  }

}
