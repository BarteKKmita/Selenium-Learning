package com.learning.pages;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.google.GoogleSearchPage;
import com.learning.pages.swaglabs.SwagLabsInventory;
import com.learning.pages.swaglabs.SwagLabsLoginPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagesConfiguration {

  @Bean
  public GoogleSearchPage provideGoogleSearchPage(@Autowired WebDriver webDriver, @Autowired PropertiesReader propertiesReader) {
    return new GoogleSearchPage(webDriver, propertiesReader);
  }

  @Bean
  public SwagLabsLoginPage provideSwagLabsLoginPage(@Autowired WebDriver webDriver, @Autowired PropertiesReader propertiesReader) {
    return new SwagLabsLoginPage(webDriver, propertiesReader);
  }

  @Bean
  public SwagLabsInventory provideSwagLabsInventoryPage(@Autowired WebDriver webDriver, @Autowired PropertiesReader propertiesReader) {
    return new SwagLabsInventory(webDriver, propertiesReader);
  }
}
