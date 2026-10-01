package Page.productPage;

import Page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By addBackpackToCartButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By pageTitle = By.xpath("//*[@id=\"header_container\"]/div[2]/span");
    private final By inventoryButtons = By.xpath("//button[contains(@class,'btn_inventory')]");
    private final By productNames = By.xpath("//div[contains(@class, 'inventory_item_name')]");
    private final By productPrices = By.xpath("//div[@class='inventory_item_price']");
    private final By removeButton = By.xpath("//button[@data-test='remove']");
    private final By sortContainer = By.cssSelector("[data-test='product-sort-container']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Sort products by name A to Z")
    public void sortProductsByNameAToZ() {
        selectSortOption("az");
    }

    @Step("Sort products by name Z to A")
    public void sortProductsByNameZToA() {
        selectSortOption("za");
    }

    @Step("Sort products by price low to high")
    public void sortProductsByPriceLowToHigh() {
        selectSortOption("lohi");
    }

    @Step("Sort products by price high to low")
    public void sortProductsByPriceHighToLow() {
        selectSortOption("hilo");
    }

    @Step("Add backpack to cart")
    public void addBackpackToCart() {
        findElement(addBackpackToCartButton).click();
    }

    @Step("Add selected products to cart")
    public void addProductsToCart(List<String> productNames) {
        clickProductButtons(productNames);
    }

    @Step("Remove selected products from cart")
    public void removeProductsFromCart(List<String> productNames) {
        clickProductButtons(productNames);
    }

    @Step("Visit product")
    public void visitProduct(String productName) {
        findElement(productNames); // wait until the products page is really loaded
        List<WebElement> products = driver.findElements(productNames);

        for (WebElement product : products) {
            String actualProductName = product.getText().trim().toLowerCase();

            if (actualProductName.contains(productName.toLowerCase())) {
                product.click();
                return;
            }
        }
    }

    @Step("Go to cart")
    public void goToCart() {
        findElement(cartIcon).click();
    }

    @Step("Get products page title")
    public String getPageTitle() {
        return findElement(pageTitle).getText();
    }

    public String getCartCount() {
        return findElement(cartBadge).getText();
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public boolean isRemoveButtonDisplayed() {
        return findElement(removeButton).isDisplayed();
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getProductPrices() {
        return driver.findElements(productPrices)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .toList();
    }

    @Step("Calculate total price for selected products")
    public double getSelectedProductsTotalPrice(List<String> selectedProductNames) {
        List<WebElement> products = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);

        double total = 0;

        for (int i = 0; i < products.size(); i++) {
            String productName = products.get(i).getText().trim().toLowerCase();

            for (String name : selectedProductNames) {
                if (productName.contains(name.toLowerCase())) {
                    String priceText = prices.get(i).getText().replace("$", "").trim();
                    total += Double.parseDouble(priceText);
                    break;
                }
            }
        }

        return total;
    }

    private void selectSortOption(String value) {
        new Select(findElement(sortContainer)).selectByValue(value);
    }

    private void clickProductButtons(List<String> names) {
        findElement(inventoryButtons); // wait until the products page is really loaded
        List<WebElement> buttons = driver.findElements(inventoryButtons);

        for (WebElement button : buttons) {
            String id = button.getAttribute("id");

            for (String name : names) {
                if (id.contains(name)) {
                    button.click();
                    break;
                }
            }
        }
    }
}
