package com.learning.pages.google;

import com.google.inject.Inject;
import com.learning.ApplicationModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = ApplicationModule.class)
public class GoogleSearchTest {

  @Inject
  private GoogleSearchPage googleSearchPage;

  @Test
  public void testGoogleSearch() {
    //Given
    googleSearchPage.open();
    //When
    googleSearchPage.search("Simodrive launch");
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    googleSearchPage.close();
  }
}
