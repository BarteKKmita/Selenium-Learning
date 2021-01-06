package com.learning.browsers;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.learning.configuration.PropertiesReader;

public class DriverModule extends PrivateModule {

  @Provides
  @Exposed
  @Singleton
  public PropertiesReader generateReader() {
    return new PropertiesReader();
  }

  @Provides
  @Exposed
  @Singleton
  public BrowserDriverGenerator generate(PropertiesReader propertiesReader) {
    return new BrowserDriverGenerator(propertiesReader);
  }

  @Override
  protected void configure() {
  }
}
