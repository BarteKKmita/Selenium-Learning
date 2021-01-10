package com.learning.browsers;

import com.google.inject.AbstractModule;
import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.learning.configuration.PropertiesReader;
import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {

  @Provides
  @Exposed
  @Singleton
  public PropertiesReader providePropertiesReader() {
    return new PropertiesReader();
  }

  @Provides
  @Exposed
  @Singleton
  public Browser provideBrowser(PropertiesReader propertiesReader) {
    return new Browser(propertiesReader);
  }

  @Provides
  @Exposed
  @Singleton
  public WebDriver provideWebDriver(PropertiesReader propertiesReader, Browser browser) {
    return browser.getWebDriver();
  }
}
