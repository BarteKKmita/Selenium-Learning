package com.learning.pages;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import com.learning.pages.google.GoogleSearchPage;
import org.openqa.selenium.WebDriver;

public class PagesModule extends PrivateModule {

  @Provides
  @Exposed
  @Singleton
  public GoogleSearchPage getGoogleSearchPage(WebDriver webDriver, PropertiesReader propertiesReader) {
    return new GoogleSearchPage(webDriver, propertiesReader);
  }

  @Override
  protected void configure() {
    install(new DriverModule());
  }
}
