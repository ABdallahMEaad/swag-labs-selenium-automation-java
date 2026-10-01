package Page.checkoutPage;

import Page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By totalPrice = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    private final By thanksMessage = By.xpath("//*[@id=\"checkout_complete_container\"]/h2");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Submit delivery form with first name: {0}, last name: {1}, zip code: {2}")
    public void submitDeliveryForm(String firstName, String lastName, String zipCode) {
        findElement(firstNameField).sendKeys(firstName);
        findElement(lastNameField).sendKeys(lastName);
        findElement(zipField).sendKeys(zipCode);
        findElement(continueButton).click();
    }

    @Step("Finish the checkout process")
    public void finishCheckout() {
        findElement(finishButton).click();
    }

    @Step("Get thanks message")
    public String getThanksMessage() {
        return findElement(thanksMessage).getText();
    }

    @Step("Get checkout error message")
    public String getErrorMessage() {
        return findElement(errorMessage).getText();
    }

    public double getItemTotal() {
        return extractPrice(findElement(itemTotal).getText());
    }

    public double getTax() {
        return extractPrice(findElement(tax).getText());
    }

    public double getTotalPrice() {
        return extractPrice(findElement(totalPrice).getText());
    }

    private double extractPrice(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}
