package com.learning.browsers;

import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrowserConfiguration {

  @Bean
  public PropertiesReader providePropertiesReader() {
    return new PropertiesReader();
  }

  @Bean
  public Browser provideBrowser(@Autowired PropertiesReader propertiesReader) {
    return new Browser(propertiesReader);
  }

  @Bean(destroyMethod = "quit")
  public WebDriver provideWebDriver(@Autowired Browser browser) {
    return browser.getWebDriver();
  }
}
