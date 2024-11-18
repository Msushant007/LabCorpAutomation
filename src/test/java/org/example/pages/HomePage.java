package org.example.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.example.utils.ReadConfig;
import org.example.utils.WaitUtils;

/**
 * @author Sushant
 */
public class HomePage {

    public WebDriver driver;
    public WaitUtils wait;
    ReadConfig readConfig =new ReadConfig();
    Logger log;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils();
        PageFactory.initElements(driver, this);
        log = org.apache.logging.log4j.LogManager.getLogger("HomePage");
    }

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement btnAcceptAllCookies;

    @FindBy(xpath = "(//*[text()='Careers'])[2]")
    WebElement lnkTopCareers;



    public void clickTopLinks(  String topLnk) {
        if (topLnk.equals("Careers")) {
            log.info("Clicking on Top Careers Link");
            JavascriptExecutor js= (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",lnkTopCareers);
        }

    }
    public void acceptAllCookies(){
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",btnAcceptAllCookies);
    }

    public String getCurrentPagePageTitle() {
        String actualTitle = driver.getTitle();
        log.info("Current page Title: "+ actualTitle);
        return actualTitle;
    }
}
