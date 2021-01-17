package com.learning.pages.google;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.learning.browsers.BrowserConfiguration;
import com.learning.pages.PagesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {BrowserConfiguration.class, PagesConfiguration.class})
public class FirstSeleniumTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private GoogleSearchPage googleSearchPage;

  @Test
  void shouldGooglePageNotBeNullWhenAutowired() {
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
}
