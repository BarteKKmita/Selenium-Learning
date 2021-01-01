package com.learning.browsers;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class DriverModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(BrowserDriver.class)
        .to(BrowserDriverGenerator.class)
        .in(Scopes.SINGLETON);
  }
}
