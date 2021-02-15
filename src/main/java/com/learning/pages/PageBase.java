package com.learning.pages;

import com.learning.browsers.Browser;
import com.learning.browsers.Loadable;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PageBase implements InitializingBean, Loadable {

  @Autowired
  protected Browser browser;

  @Override
  public void afterPropertiesSet() {
    PageFactory.initElements(browser.getElementLocator(), this);
  }
}
