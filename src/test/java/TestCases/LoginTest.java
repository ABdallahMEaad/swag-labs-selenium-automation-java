package TestCases;

import Data.Data;
import DriverFactory.Base;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs")
@Feature("Login")
public class LoginTest extends Base {


    @Story("Valid Login")
    @Description("Verify that a user can successfully log in with valid credentials.")
    @Test(testName = "Verify Valid Login With Different Users", dataProvider = "credentials", dataProviderClass = Data.class, groups = "smoke")
    public void verifyValidLogin(String username, String password) {
        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.getProductsTitle().contains("Products"),
                "Products page was not displayed after successful login."
        );
    }



    @Story("Invalid Login")
    @Description("Verify that an error message is displayed when invalid credentials are provided.")
    @Test(testName = "Verify Error Message For Invalid Login", dataProvider = "invalidLoginDataWitherrorMessages", dataProviderClass = Data.class, groups = {"regression"})
    public void verifyInvalidLogin(String username, String password,String errorMessage) {
        loginPage.login(username, password);
        Assert.assertTrue(
                loginPage.getErrorMessage().contains(errorMessage),
                "Login error message was not displayed after invalid login attempt."
        );
    }


    @Story("valid Login With DataConfig")
    @Description("Verify that a user can successfully log in with valid credentials")
    @Test(testName = "Verify Valid Login With Data From Config File", groups = {"regression"})
    public void validLoginWithDataConfig() {

        loginPage.login(configHandler.getValue("username"),
                        configHandler.getValue("password")
        );

        Assert.assertTrue(
                loginPage.getProductsTitle().contains("Products"),
                "Products page was not displayed after successful login."
        );
    }
}
