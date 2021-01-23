package com.learning;

import static org.testng.Assert.assertNotNull;

import com.learning.browsers.Browser;
import com.learning.pages.google.GoogleSearchPage;
import com.learning.pages.swaglabs.SwagLabsInventoryPage;
import com.learning.pages.swaglabs.SwagLabsLoginPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = TestsConfiguration.class)

public class SpringConfigurationIntegrationTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private GoogleSearchPage googleSearchPage;
  @Autowired
  private Browser browser;
  @Autowired
  private SwagLabsInventoryPage swagLabsInventoryPage;
  @Autowired
  private SwagLabsLoginPage swagLabsLoginPage;

  @Test
  void shouldObjectsNotBeNullWhenAutowired() {
    assertNotNull(googleSearchPage);
    assertNotNull(browser);
    assertNotNull(swagLabsInventoryPage);
    assertNotNull(swagLabsLoginPage);
  }
}
