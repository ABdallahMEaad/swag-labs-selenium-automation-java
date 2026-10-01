package TestCases;

import Data.Data;
import DriverFactory.Base;
import Page.cartPage.CartPage;
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
@Feature("Shopping Cart")
public class CartTest extends Base {

    private ProductsPage productsPage;
    private CartPage cartPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndCreatePages() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    @Story("Shopping Cart")
    @Description("Verify that the product name displayed in the cart matches the product that was added.")
    @Test(testName = "Verify Cart Item Name Matches Added Product", groups = "regression")
    public void verifyCartItemNameMatchesAddedProduct() {
        productsPage.addBackpackToCart();
        productsPage.goToCart();

        Assert.assertTrue(cartPage.getFirstCartItemText().contains("Backpack"));
    }

    @Story("Shopping Cart")
    @Description("Verify that the user can continue shopping after navigating to the cart.")
    @Test(testName = "Verify Continue Shopping From Cart Page", groups = "regression")
    public void verifyContinueShoppingAfterGoingToCart() {
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        cartPage.continueShopping();

        Assert.assertTrue(productsPage.getPageTitle().contains("Products"));
    }

    @Story("Shopping Cart")
    @Description("Verify that multiple selected products can be added to the shopping cart and the cart count is updated correctly.")
    @Test(testName = "Verify Adding Products To Cart Updates Cart Count", dataProvider = "productsData", dataProviderClass = Data.class, groups = "regression")
    public void verifyAddProductsToCart(List<String> productNames) {
        productsPage.addProductsToCart(productNames);

        Assert.assertEquals(productsPage.getCartCount(), Integer.toString(productNames.size()));
    }

    @Story("Shopping Cart")
    @Description("Verify that selected products can be removed from the shopping cart.")
    @Test(testName = "Verify Removing Products From Cart", dataProvider = "productsData", dataProviderClass = Data.class, groups = "regression")
    public void verifyRemoveProductsFromCart(List<String> productNames) {
        productsPage.addProductsToCart(productNames);
        productsPage.goToCart();
        cartPage.continueShopping();
        productsPage.removeProductsFromCart(productNames);

        Assert.assertFalse(productsPage.isCartBadgeDisplayed());
    }

    @Story("Shopping Cart")
    @Description("Verify that the Remove button is displayed for products that have already been added to the cart.")
    @Test(testName = "Verify Remove Button Is Displayed For Added Products", dataProvider = "productsData", dataProviderClass = Data.class, groups = "regression")
    public void verifyRemoveButtonDisplayedForAddedProducts(List<String> productNames) {
        productsPage.addProductsToCart(productNames);

        for (String productName : productNames) {
            productsPage.visitProduct(productName);

            Assert.assertTrue(productsPage.isRemoveButtonDisplayed());

            driver.navigate().back();
        }
    }
}
