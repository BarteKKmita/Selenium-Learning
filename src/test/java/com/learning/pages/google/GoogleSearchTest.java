package com.learning.pages.google;

import com.learning.TestsConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {TestsConfiguration.class})
public class GoogleSearchTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private GoogleSearchPage googleSearchPage;

  @Test
  public void testGoogleSearch() {
    //Given
    googleSearchPage.open();
    //When
    googleSearchPage.search("Simodrive launch");
  }
}
