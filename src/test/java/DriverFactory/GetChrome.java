package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetChrome {


    private static WebDriver driver=null;

    public static WebDriver getChromeDriver (){

        if (driver == null){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        if (ChooseDriver.isHeadless()) {
            chromeOptions.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
        }
        driver= new ChromeDriver(chromeOptions);
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
