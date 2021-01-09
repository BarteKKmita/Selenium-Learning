package com.learning.pages.swaglabs;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class OfferingCard {

  @FindBy(css = "button[class='btn_primary btn_inventory']")
  private WebElement addToCartButton;

  @FindBy(className = "inventory_item_name")
  private WebElement itemName;

  public OfferingCard(WebElement rootElement) {
    PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
  }

  public void addToCart() {
    addToCartButton.click();
  }

  public String getItemName() {
    return itemName.getText();
  }
}
