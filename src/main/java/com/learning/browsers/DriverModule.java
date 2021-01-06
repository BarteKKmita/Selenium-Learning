package com.learning.browsers;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;

public class DriverModule extends PrivateModule {

  @Provides
  @Exposed
  @Singleton
  public PropertiesReader providePropertiesReader() {
    return new PropertiesReader();
  }

  @Provides
  @Exposed
  @Singleton
  public WebDriver provideWebDriver(PropertiesReader propertiesReader) {
    return new WebDriverFactory(propertiesReader).getWebDriver();
  }

  @Override
  protected void configure() {
  }
}
