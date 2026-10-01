package DriverFactory;

import Page.loginPage.LoginPage;
import Utils.ConfigHandler;
import io.qameta.allure.Allure;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Base {
    public LoginPage loginPage;

    public WebDriver driver;

    public ConfigHandler configHandler;

    private static String browserVersion = "N/A";

    // alwaysRun = true: without it TestNG skips this method whenever tests are filtered by groups (smoke / regression)
    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() {
        configHandler = new ConfigHandler("src/main/resources/config.json");
        driver= ChooseDriver.getWebdriver(configHandler.getValue("browserName"));
        try {
            browserVersion = ((HasCapabilities) driver).getCapabilities().getBrowserVersion();
        } catch (Exception ignored) {
        }
        driver.manage().window().maximize();
        driver.get(configHandler.getValue("url"));
        loginPage=new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        attachScreenshot(result);
        if (configHandler != null) {
            ChooseDriver.quitDriver(configHandler.getValue("browserName"));
        }
    }

    // Writes the "Environment" widget of the Allure report (environment.properties inside the allure results folder)
    @AfterSuite(alwaysRun = true)
    public void writeAllureEnvironment() {
        ConfigHandler config = new ConfigHandler("src/main/resources/config.json");

        Properties environment = new Properties();
        environment.setProperty("Tester", config.getValue("tester"));
        environment.setProperty("Run Date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")));
        environment.setProperty("Operating System", System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ")");
        environment.setProperty("Browser", config.getValue("browserName"));
        environment.setProperty("Browser Version", browserVersion);
        environment.setProperty("Execution Mode", ChooseDriver.isHeadless() ? "Headless" : "Headed");
        environment.setProperty("Application URL", config.getValue("url"));
        environment.setProperty("Java Version", System.getProperty("java.version"));

        try {
            Path resultsDirectory = Path.of(System.getProperty("allure.results.directory", "target/allure-results"));
            Files.createDirectories(resultsDirectory);
            try (Writer writer = Files.newBufferedWriter(resultsDirectory.resolve("environment.properties"), StandardCharsets.UTF_8)) {
                environment.store(writer, "Allure environment");
            }
        } catch (Exception e) {
            System.out.println("Could not write Allure environment file: " + e.getMessage());
        }
    }

    // Takes a screenshot after every test (pass or fail) and attaches it to the Allure report as a PNG image.
    // The name must end with ".png" so the Allure report knows it is an image and shows it inline.
    // It is done in @AfterMethod (before the browser is closed) so it always runs, whatever the groups / listeners.
    private void attachScreenshot(ITestResult result) {
        if (driver == null) {
            System.out.println("Screenshot skipped: the browser was not started.");
            return;
        }
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String status = switch (result.getStatus()) {
                case ITestResult.SUCCESS -> "Passed";
                case ITestResult.FAILURE -> "Failed";
                default -> "Skipped";
            };
            Allure.attachment("Screenshot - " + status + ".png", new ByteArrayInputStream(screenshot));
            System.out.println("Screenshot attached to Allure (" + status + "): " + result.getName());
        } catch (Exception e) {
            System.out.println("Could not take/attach screenshot: " + e);
            e.printStackTrace();
        }
    }
}
