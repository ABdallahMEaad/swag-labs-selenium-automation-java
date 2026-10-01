package TestCases;

import Data.Data;
import DriverFactory.Base;
import Page.cartPage.CartPage;
import Page.checkoutPage.CheckoutPage;
import Page.loginPage.LoginPage;
import Page.productPage.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Swag Labs")
@Feature("Checkout")
public class CheckoutTest extends Base {

    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndCreatePages() {

        loginPage.login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Story("Checkout")
    @Description("Verify that a user can add an item to the cart and successfully complete the checkout process with valid delivery information.")
    @Test(testName = "Verify Complete Checkout With Valid Delivery Data", dataProvider = "deliveryData", dataProviderClass = Data.class, groups = "regression")
    public void verifyCompleteCheckoutWithDeliveryData(String firstName, String lastName, String zip) {
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        cartPage.goToCheckout();
        checkoutPage.submitDeliveryForm(firstName, lastName, zip);
        checkoutPage.finishCheckout();

        Assert.assertTrue(checkoutPage.getThanksMessage().contains("Thank"));
    }

    @Story("Checkout")
    @Description("Verify the checkout page can be accessed when the shopping cart is empty.")
    @Test(testName = "Verify Checkout Page Is Accessible With Empty Cart", groups = "regression")
    public void verifyCheckoutPageAccessibleWithEmptyCart() {
        productsPage.goToCart();
        cartPage.goToCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"));
    }

    @Story("Checkout Validation")
    @Description("Verify that the checkout process validates the required First Name field.")
    @Test(testName = "Verify First Name Is Required For Checkout", groups = "regression")
    public void verifyFirstNameIsRequiredForCheckout() {
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        cartPage.goToCheckout();
        checkoutPage.submitDeliveryForm("", "Meaad", "12345");

        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Story("Checkout")
    @Description("Verify that the checkout subtotal and total price are calculated correctly based on product prices and tax.")
    @Test(testName = "Verify Subtotal And Total Price Calculation", dataProvider = "productsData", dataProviderClass = Data.class, groups = "regression")
    public void verifyTotalPrice(List<String> productNames) {
        productsPage.addProductsToCart(productNames);

        double expectedSubtotal = productsPage.getSelectedProductsTotalPrice(productNames);

        productsPage.goToCart();
        cartPage.goToCheckout();
        checkoutPage.submitDeliveryForm("Abdallah", "Meaad", "12345");

        double actualSubtotal = checkoutPage.getItemTotal();
        double actualTax = checkoutPage.getTax();
        double actualTotal = checkoutPage.getTotalPrice();

        double expectedTotal = expectedSubtotal + actualTax;

        System.out.println(
                "Expected Subtotal: " + expectedSubtotal +
                        " | Actual Subtotal: " + actualSubtotal +
                        " | Tax: " + actualTax +
                        " | Expected Total: " + expectedTotal +
                        " | Actual Total: " + actualTotal
        );

        Assert.assertEquals(actualSubtotal, expectedSubtotal, 0.01, "Subtotal is incorrect");
        Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total price is incorrect");
    }
}
