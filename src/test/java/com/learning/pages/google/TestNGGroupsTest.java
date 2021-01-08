package com.learning.pages.google;

import com.google.inject.Inject;
import com.learning.ApplicationModule;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Guice(modules = ApplicationModule.class)
public class TestNGGroupsTest {

  @Inject
  private GoogleSearchPage googleSearchPage;

  @Test(groups = {"sanity"})
  void shouldGoToGoogle() {
    googleSearchPage.open();
  }

  @Test(priority = 1)
  void test2() {
    System.out.println("This is test 2");
  }

  @Test(groups = {"sanity"})
  void test3() {
    System.out.println("This is test 3");
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
    googleSearchPage.close();
  }
}
