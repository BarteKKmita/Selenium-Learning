package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;

import com.learning.browsers.Browser;
import com.learning.browsers.BrowserConfiguration;
import com.learning.pages.PagesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {BrowserConfiguration.class, PagesConfiguration.class})
public class SwagLabsInventoryPageTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private Browser browser;
  @Autowired
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
