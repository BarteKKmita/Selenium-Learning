package com.learning.pages;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.learning.browsers.DriverModule;
import com.learning.configuration.PropertiesReader;
import com.learning.pages.google.GoogleSearchPage;
import com.learning.pages.swaglabs.SwagLabsInventory;
import com.learning.pages.swaglabs.SwagLabsLoginPage;
import org.openqa.selenium.WebDriver;

public class PagesModule extends PrivateModule {

  @Provides
  @Exposed
  @Singleton
  public GoogleSearchPage provideGoogleSearchPage(WebDriver webDriver, PropertiesReader propertiesReader) {
    return new GoogleSearchPage(webDriver, propertiesReader);
  }

  @Provides
  @Exposed
  @Singleton
  public SwagLabsLoginPage provideSwagLabsLoginPage(WebDriver webDriver, PropertiesReader propertiesReader) {
    return new SwagLabsLoginPage(webDriver, propertiesReader);
  }


  @Provides
  @Exposed
  @Singleton
  public SwagLabsInventory provideSwagLabsInventoryPage(WebDriver webDriver, PropertiesReader propertiesReader) {
    return new SwagLabsInventory(webDriver, propertiesReader);
  }

  @Override
  protected void configure() {
    install(new DriverModule());
  }
}
