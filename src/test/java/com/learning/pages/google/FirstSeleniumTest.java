package com.learning.pages.google;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.google.inject.Inject;
import com.learning.ApplicationModule;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = ApplicationModule.class)
public class FirstSeleniumTest {

  @Inject
  private GoogleSearchPage googleSearchPage;

  @Test
  void shouldGooglePageNotBeNullWhenInjected() {
    assertNotNull(googleSearchPage);
  }

  @lombok.SneakyThrows
  @Test
  void shouldFindElementOnGoogleWebSite() {
    getGoogleWebsite();
    googleSearchPage.waitForPageToLoad();
    googleSearchPage.searchGoogle("Getting grip on it! ");
  }

  @Test
  void shouldFind8InputElementsOnGoogle() {
    //Given
    int expectedInputElements = 8;
    //When
    getGoogleWebsite();
    int actualInputElements = googleSearchPage.getAllInputsCount();
    //Then
    assertEquals(expectedInputElements, actualInputElements);
  }

  private void getGoogleWebsite() {
    googleSearchPage.openGoogleWebsite();
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    googleSearchPage.close();
  }
}
