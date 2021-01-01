package com.learning.pages.swaglabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsInventory {

  public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

  //TODO
  // find addToCartButton without arrays
  @FindBy(css = "a#item_1_img_link")
  private WebElement addToCartButton;

  public SwagLabsInventory(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  public void performAddToCart() {
    addToCartButton.click();
  }

}
