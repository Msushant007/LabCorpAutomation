package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

/**
 * @author Sushant
 */
public class DriverSetup {



    public  static ReadConfig readConfig = new ReadConfig();
    public static  WebDriver  driver     =null;

    public static Logger     logger;
    public  static DriverSetup driverInit = null;

    private DriverSetup() {  // Constructor created for initialization singleton class

    }

    public static DriverSetup getInstance() { //Singleton get instance method.
        if (driverInit == null) {
            driverInit = new DriverSetup();
        }
        return driverInit;
    }

    public static WebDriver webDriverOpen(String browserName) {

        logger = Logger.getLogger("Web Driver Class invoked...");
        if(driver==null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                //System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriver());
                driver = new ChromeDriver();
                logger.info("Driver invoked :" + browserName);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", readConfig.getfireFoxDriver());
                driver = new FirefoxDriver();
                logger.info("Driver invoked :" + browserName);
            }
        }
        return driver;
    }



}
