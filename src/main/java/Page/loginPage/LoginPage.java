package Page.loginPage;

import Page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By userNameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By productsTitle = By.className("title");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUserNameField() {
        return findElement(userNameField);
    }

    public WebElement getPasswordField() {
        return findElement(passwordField);
    }

    public WebElement getLoginButton() {
        return findElement(loginButton);
    }

    @Step("Get products page title")
    public String getProductsTitle() {
        return findElement(productsTitle).getText();
    }

    @Step("Get login error message")
    public String getErrorMessage() {
        return findElement(errorMessage).getText();
    }

    @Step("Login with username: {0}")
    public void login(String username, String password) {
        getUserNameField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }
}
