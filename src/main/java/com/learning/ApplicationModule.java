package com.learning;

import com.google.inject.AbstractModule;
import com.learning.pages.PagesModule;

public class ApplicationModule extends AbstractModule {

  @Override
  protected void configure() {
    install(new PagesModule());
  }
}
