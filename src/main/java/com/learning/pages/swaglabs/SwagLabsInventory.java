package com.learning.pages.swaglabs;

import com.learning.configuration.PropertiesReader;
import com.learning.pages.PageBase;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SwagLabsInventory extends PageBase {

  private final PropertiesReader propertiesReader;
  private static final String INVENTORY_PAGE_URL_PROPERTY = "swagLabsInventoryURL";

  @FindBy(className = "inventory_item")
  private List<WebElement> offeringCards;

  @FindBy(css = "span[class$=shopping_cart_badge")
  private WebElement cart;

  public SwagLabsInventory(WebDriver driver, PropertiesReader propertiesReader) {
    super(driver);
    this.propertiesReader = propertiesReader;
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
    return propertiesReader.getProperty(INVENTORY_PAGE_URL_PROPERTY);
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
