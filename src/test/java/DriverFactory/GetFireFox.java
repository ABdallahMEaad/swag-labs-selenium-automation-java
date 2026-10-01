package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetFireFox {
    public static WebDriver driver=null;
    public static WebDriver FirefoxDriver() {

        if (driver == null){
            FirefoxOptions FirefoxOptions = new FirefoxOptions();
            FirefoxOptions.addArguments("--private");
            if (ChooseDriver.isHeadless()) {
                FirefoxOptions.addArguments("-headless", "--width=1920", "--height=1080");
            }

            driver= new FirefoxDriver(FirefoxOptions);
        }
        return driver;

    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver=null ;
        }
    }


    }


