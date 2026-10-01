package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdgeDriver {

    public static WebDriver driver=null;

    public static WebDriver getEdgeDriver() {


        if (driver == null){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--inprivate");
            if (ChooseDriver.isHeadless()) {
                edgeOptions.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
            }

            driver= new EdgeDriver(edgeOptions);
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
