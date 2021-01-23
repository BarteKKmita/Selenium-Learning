package com.learning.pages;

import com.learning.browsers.Browser;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PageBase implements InitializingBean {

  @Autowired
  protected Browser browser;

  @Override
  public void afterPropertiesSet() {
    PageFactory.initElements(browser.getWebDriver(), this);
  }
}
