package com.learning.pages.google;

import com.learning.TestsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@ContextConfiguration(classes = TestsConfiguration.class)
public class TestNGGroupsTest extends AbstractTestNGSpringContextTests {

  @Autowired
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
}
