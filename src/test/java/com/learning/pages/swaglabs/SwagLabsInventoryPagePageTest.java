package com.learning.pages.swaglabs;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import com.learning.TestsConfiguration;
import com.learning.browsers.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = TestsConfiguration.class)
public class SwagLabsInventoryPagePageTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private Browser browser;
  @Autowired
  private SwagLabsInventoryPage swagLabsInventoryPage;

  @Test
  void shouldObjectsNotBeNullWhenAutowired() {
    assertNotNull(browser);
    assertNotNull(swagLabsInventoryPage);
  }

  @Test
  void shouldGoToShopPage() throws InterruptedException {
    //Given
    String expectedURL = swagLabsInventoryPage.getURL();
    swagLabsInventoryPage.open();
    //Then
    assertEquals(browser.getCurrentPageURL(), expectedURL);
  }

  @Test
  void shouldAddItemToCart() {
    //Given
    String itemName = "Sauce Labs Onesie";
    int expectedItemsInCart = 1;
    //When
    swagLabsInventoryPage.open();
    swagLabsInventoryPage.addItemToCart(itemName);
    //Then
    int actualItemsInCart = swagLabsInventoryPage.countItemsInCart();
    assertEquals(actualItemsInCart, expectedItemsInCart);
  }
}
