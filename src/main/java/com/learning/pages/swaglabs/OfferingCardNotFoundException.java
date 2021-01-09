package com.learning.pages.swaglabs;

import org.openqa.selenium.NoSuchElementException;

public class OfferingCardNotFoundException extends NoSuchElementException {

  public OfferingCardNotFoundException(String reason) {
    super(reason);
  }
}
