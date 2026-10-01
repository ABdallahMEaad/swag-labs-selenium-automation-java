package Page.cartPage;

import Page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By cartItem = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to checkout")
    public void goToCheckout() {
        findElement(checkoutButton).click();
    }

    @Step("Continue shopping")
    public void continueShopping() {
        findElement(continueShoppingButton).click();
    }

    @Step("Get first cart item text")
    public String getFirstCartItemText() {
        return findElement(cartItem).getText();
    }
}
