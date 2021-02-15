package com.learning.pages.swaglabs;

import com.learning.pages.PageBase;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SwagLabsInventoryPage extends PageBase {

  private final String swagLabsInventoryURL;

  @FindBy(className = "inventory_item")
  private List<WebElement> offeringCards;
  @FindBy(css = "span[class='fa-layers-counter shopping_cart_badge']")
  private WebElement cart;
  @FindBy(css = "img[class='inventory_item_img']")
  private List<WebElement> itemPictures;

  public SwagLabsInventoryPage(@Value("${swagLabsInventoryURL}") String swagLabsInventoryURL) {
    this.swagLabsInventoryURL = swagLabsInventoryURL;
  }

  public void open() {
    browser.open(swagLabsInventoryURL, this);
  }

  public void addItemToCart(String itemName) {
    OfferingCard item = getItem(itemName);
    item.addToCart();
  }

  public int countItemsInCart() {
    return Integer.parseInt(cart.getText());
  }

  String getURL() {
    return swagLabsInventoryURL;
  }

  @Override
  public boolean isLoaded() {
    return browser.waitForElementsToLoad(offeringCards.toArray(WebElement[]::new));
  }

  public boolean arePicturesLoaded() {
    return itemPictures.stream()
        .allMatch(picture ->
            picture.getSize().height == 224 && picture.getSize().width == 179);
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
