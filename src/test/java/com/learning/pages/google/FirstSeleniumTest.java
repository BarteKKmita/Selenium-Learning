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
    googleSearchPage.open();
    googleSearchPage.search("Getting grip on it! ");
  }

  @Test
  void shouldFind8InputElementsOnGoogle() {
    //Given
    int expectedInputElements = 8;
    //When
    googleSearchPage.open();
    int actualInputElements = googleSearchPage.getAllInputsCount();
    //Then
    assertEquals(expectedInputElements, actualInputElements);
  }

  @AfterClass(alwaysRun = true)
  void tearDown() {
    googleSearchPage.close();
  }
}
