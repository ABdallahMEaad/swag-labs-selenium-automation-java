package TestCases;

import DriverFactory.Base;
import Page.loginPage.LoginPage;
import Page.productPage.ProductsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Epic("Swag Labs")
@Feature("Products")
public class ProductsTest extends Base {

    private ProductsPage productsPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndCreateProductsPage() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        productsPage = new ProductsPage(driver);
    }

    @Story("Product Sorting")
    @Description("Verify that products can be sorted alphabetically from A to Z.")
    @Test(testName = "Verify Products Sorted By Name (A to Z)", groups = "regression")
    public void verifySortByNameAToZ() {
        productsPage.sortProductsByNameAToZ();

        List<String> actualNames = productsPage.getProductNames();

        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(actualNames, expectedNames);
    }

    @Story("Product Sorting")
    @Description("Verify that products can be sorted alphabetically from Z to A.")
    @Test(testName = "Verify Products Sorted By Name (Z to A)", groups = "regression")
    public void verifySortByNameZToA() {
        productsPage.sortProductsByNameZToA();

        List<String> actualNames = productsPage.getProductNames();

        List<String> expectedNames = new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(actualNames, expectedNames);
    }

    @Story("Product Sorting")
    @Description("Verify that products can be sorted by price from low to high.")
    @Test(testName = "Verify Products Sorted By Price (Low to High)", groups = "regression")
    public void verifySortByPriceLowToHigh() {
        productsPage.sortProductsByPriceLowToHigh();

        List<Double> actualPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        System.out.println("actualPrices:" + actualPrices + "||expectedPrices" + expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);
    }

    @Story("Product Sorting")
    @Description("Verify that products can be sorted by price from high to low.")
    @Test(testName = "Verify Products Sorted By Price (High to Low)", groups = "regression")
    public void verifySortByPriceHighToLow() {
        productsPage.sortProductsByPriceHighToLow();

        List<Double> actualPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        System.out.println("actualPrices:" + actualPrices + "||expectedPrices" + expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);
    }
}
