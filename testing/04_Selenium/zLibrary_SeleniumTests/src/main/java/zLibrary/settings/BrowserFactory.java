package zLibrary.settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import zLibrary.PageConstants;

import java.util.concurrent.TimeUnit;

public class BrowserFactory implements PageConstants {

    private static WebDriver driver;

    public static WebDriver startBrowser(String browserName, String URL) {
        if(browserName.equalsIgnoreCase("firefox")) {
            //System.setProperty("webdriver.gecko.driver", DRIVER_PATH_FIREFOX_64);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("ie")) {
            //System.setProperty("webdriver.ie.driver", DRIVER_PATH_IE);
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            //System.setProperty("webdriver.chrome.driver", DRIVER_PATH_CHROME);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //driver.manage().window().fullscreen();
        driver.get(URL);
        return driver;
    }
}
