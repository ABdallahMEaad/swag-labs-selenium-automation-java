package DriverFactory;

import org.openqa.selenium.WebDriver;

public class ChooseDriver {

    // Headless mode is only used by the pipeline (mvn test -Dheadless=true). Locally the browser opens normally.
    public static boolean isHeadless() {
        return Boolean.getBoolean("headless") || "true".equalsIgnoreCase(System.getenv("HEADLESS"));
    }

    public static WebDriver getWebdriver(String browserName) {

        WebDriver driver;
        switch (browserName.toLowerCase().trim()) {

            case "chrome":
                driver = GetChrome.getChromeDriver();
                break;

            case "firefox":
                driver = GetFireFox.FirefoxDriver();
                break;

            case "edge":
                driver = GetEdgeDriver.getEdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid Browser");
        }

        return driver;
    }


    public static void quitDriver(String browserName) {


        switch (browserName.toLowerCase().trim()) {

            case "chrome":
                GetChrome.closeDriver();
                break;

            case "firefox":
                GetFireFox.closeDriver();
                break;

            case "edge":
                GetEdgeDriver.closeDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid Browser");
        }


    }
}



