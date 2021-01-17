package com.learning.pages.swaglabs;

import com.learning.browsers.Browser;
import com.learning.pages.PageBase;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwagLabsInventoryPage extends PageBase {

  private final String swagLabsInventoryURL;

  @FindBy(className = "inventory_item")
  private List<WebElement> offeringCards;
  @FindBy(css = "span[class='fa-layers-counter shopping_cart_badge']")
  private WebElement cart;

  public SwagLabsInventoryPage(Browser browser, @Value("${swagLabsInventoryURL}") String swagLabsInventoryURL) {
    super(browser);
    this.swagLabsInventoryURL = swagLabsInventoryURL;
  }

  void open() {
    driver.get(getURL());
    waitForPageToLoad();
  }

  public void addItemToCart(String itemName) {
    OfferingCard item = getItem(itemName);
    item.addToCart();
  }

  public int countItemsInCart() {
    return Integer.parseInt(cart.getText());
  }

  @Override
  public boolean waitForPageToLoad() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    return wait.until(ExpectedConditions
        .visibilityOfAllElements(offeringCards))
        .stream()
        .allMatch(Objects::nonNull);
  }

  String getURL() {
    return swagLabsInventoryURL;
  }

  private OfferingCard getItem(String itemName) {
    return getAllOfferingCards().stream()
        .filter(offeringCard -> itemName.equals(offeringCard.getItemName()))
        .findFirst()
        .orElseThrow(() -> new OfferingCardNotFoundException("There is no offering card for item " + itemName));
  }

  private List<OfferingCard> getAllOfferingCards() {
    return offeringCards.stream()
        .map(OfferingCard::new)
        .collect(Collectors.toList());
  }
}
