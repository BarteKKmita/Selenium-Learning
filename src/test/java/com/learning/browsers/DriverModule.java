package com.learning.browsers;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DriverModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(BrowserDriver.class)
        .annotatedWith(Names.named("ChromeDriver"))
        .to(ChromeDriverGenerator.class)
        .in(Scopes.SINGLETON);
    bind(BrowserDriver.class)
        .annotatedWith(Names.named("EdgeDriver"))
        .to(EdgeDriverGenerator.class);
  }

}
