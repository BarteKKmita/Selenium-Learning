package com.learning.browsers;

public class BrowserNotSupportedException extends IllegalArgumentException {

  public BrowserNotSupportedException(String message) {
    super(message);
  }
}
