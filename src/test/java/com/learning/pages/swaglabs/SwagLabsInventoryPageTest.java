package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;

import com.google.inject.Inject;
import com.learning.ApplicationModule;
import com.learning.browsers.Browser;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = ApplicationModule.class)
public class SwagLabsInventoryPageTest {

  @Inject
  private Browser browser;
  @Inject
  private SwagLabsInventory swagLabsInventory;

  @Test
  void shouldGoToShopPage() throws InterruptedException {
    //Given
    String expectedURL = swagLabsInventory.getURL();
    //When
    swagLabsInventory.open();
    //Then
    assertEquals(browser.getCurrentPageURL(), expectedURL);
  }

  @Test
  void shouldAddItemToCart() {
    //Given
    String itemName = "Sauce Labs Onesie";
    int expectedItemsInCart = 1;
    //When
    swagLabsInventory.open();
    swagLabsInventory.addItemToCart(itemName);
    int actualItemsInCart = swagLabsInventory.countItemsInCart();
    //Then
    assertEquals(actualItemsInCart, expectedItemsInCart);
  }
}
