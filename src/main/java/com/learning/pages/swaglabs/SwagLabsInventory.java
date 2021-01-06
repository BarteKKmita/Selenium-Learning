package com.learning.pages.swaglabs;

import com.learning.pages.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SwagLabsInventory extends PageBase {

  public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";

  //TODO
  // find addToCartButton without arrays
  @FindBy(css = "a#item_1_img_link")
  private WebElement addToCartButton;

  public SwagLabsInventory(WebDriver driver) {
    super(driver);
  }

  @Override
  public boolean waitForPageToLoad() {
    return true;
  }

  public void performAddToCart() {
    addToCartButton.click();
  }

}
