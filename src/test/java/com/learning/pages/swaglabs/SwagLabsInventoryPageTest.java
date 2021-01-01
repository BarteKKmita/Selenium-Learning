package com.learning.pages.swaglabs;

import com.google.inject.Inject;
import com.learning.browsers.BrowserDriver;
import com.learning.browsers.DriverModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Guice;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore(value = "This test case will be added after solving ADD TO CARD btn issue.")
@Guice(modules = DriverModule.class)
public class SwagLabsInventoryPageTest {

  @Inject
  private BrowserDriver browserDriver;

  private WebDriver driver;
  private SwagLabsInventory swagLabsInventory;

  @BeforeClass
  void setUp() {
    driver = browserDriver.getDriver();
    swagLabsInventory = new SwagLabsInventory(driver);
  }

  @Test
  void shouldGoToShopPageWhenSuccessfulLogin() {
    driver.get(SwagLabsInventory.INVENTORY_PAGE_URL);
    swagLabsInventory.performAddToCart();
  }

}
