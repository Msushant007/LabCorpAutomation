package org.example.pages;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.example.utils.ReadConfig;
import org.example.utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sushant
 */
public class CareersPage {

    public WebDriver driver;
    public WaitUtils wait;
    ReadConfig readConfig = new ReadConfig();
    Logger log;
    Actions ac;

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils();
        PageFactory.initElements(driver, this);
        log = org.apache.logging.log4j.LogManager.getLogger("CareersPage");
    }

    @FindBy(xpath = "//button[@data-access='seeNext']")
    WebElement btnViewMore;
    @FindBy(xpath = "(//div[@class='job-title addtocart-active'])[1]")
    WebElement eleJobTitle;
    @FindBy(xpath = "//input[@placeholder='Search job title or location']")
    WebElement txaSearchBox;
    @FindBy(xpath = "//h1[@class='job-title']")
    WebElement labelJobTitle;
    @FindBy(xpath = "(//span[contains(@class,'job-location')])[1]")
    WebElement labelJobLocation;
    @FindBy(xpath = "(//span[contains(@class,'jobId')])[1]")
    WebElement labelJobId;
    @FindBy(xpath = "//*[@class='see-multiple-loc-btn ph-a11y-multi-location au-target']")
    WebElement lnkSellAllLocation;
    @FindBys(@FindBy(xpath = "//*[contains(@class,'location-list au-target')]//child::span[2]"))
    List<WebElement> listOfLocation;
    @FindBy(xpath = "(//a[@ph-tevent='apply_click'])[2]")
    WebElement btnBottomApplyNow;
    @FindBy(xpath = "//*[@id='mainContent']//h3")
    WebElement labelJobTitleOnApplicationPg;
    @FindBy(xpath = "//button[@data-automation-id='navigationItem-Careers Home']")
    WebElement lnkCareerHome;


    public String getFirstJobTileText() {
        String jobTitle = "NA";
        try {
            Actions ac = new Actions(driver);
            wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), btnViewMore);
            ac.scrollToElement(btnViewMore).build().perform();
            wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), eleJobTitle);
            jobTitle = eleJobTitle.getText();
//        log.info("get First Job Title for Search" + jobTitle);
            return jobTitle;
        } catch (Exception e) {
            log.error("Exception Found: " + e.getMessage());
        }
        return jobTitle;
    }

    public void searchJobWithJobTitle(String jobTitle) throws InterruptedException {
        ac = new Actions(driver);
        wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), txaSearchBox);
        txaSearchBox.clear();
        log.info("Job Search with Title: " + jobTitle);
        txaSearchBox.sendKeys(jobTitle);
        Thread.sleep(1000);
        ac.sendKeys(Keys.ENTER).build().perform();
    }

    public List<String> getAllJobsFromSearchResult() {
        List<WebElement> listOfSearchJobTitle = driver.findElements(By.xpath("//*[@data-ph-at-id='jobs-list']//child::*[@data-ph-at-job-title-text]"));
        List<String> ls = new ArrayList<>();
        for (WebElement el : listOfSearchJobTitle) {
            ls.add((String) el.getText());
        }
        log.info(" Result list for Job Searched " + ls);
        return ls;
    }

    public void getAndSelectFirstJobsFromSearchResult(String jobTitle) {
        WebElement jobVal = driver.findElement(By.xpath("//a[@data-ph-at-job-title-text='" + jobTitle + "']"));
        jobVal.click();

        log.info(" Select the Job from Search Result: " + jobTitle);
    }

    public String getTheJobDetailsForAssert(String assertVal) {
        String data = "NA";
        try {
            if (assertVal.equals("JobTitle")) {
                wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), labelJobTitle);
                data = labelJobTitle.getText();
                log.info("Job Title Visible on Page: " + data);
                return data;
            } else if (assertVal.equals("JobLocation")) {
                wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), labelJobLocation);
                data = labelJobLocation.getText();
                log.info("Job Location Visible on Page: " + data);
                return data;
            } else if (assertVal.equals("JobId")) {
                wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), labelJobId);
                data = labelJobId.getText();
                log.info("Job Id Visible on Page: " + data);
                return data;
            }

        } catch (Exception e) {
            log.error("Exception Found:" + e.getMessage());
        }
        return data;
    }

    public List<String> getTheJobLocationForAssert() {
        List<String> listOfLoc = new ArrayList<>();
        try {
            wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), lnkSellAllLocation);
            lnkSellAllLocation.click();
            wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), listOfLocation.get(0));
            int count=1;
            for (WebElement el : listOfLocation) {
                String location=el.getText();
                listOfLoc.add(location );
                log.info(" Location-"+count +": "+ location);
                count++;
            }
            ac = new Actions(driver);
            ac.sendKeys(Keys.ESCAPE).build().perform();
          //  log.info("All Locations: "+ listOfLoc);
        }catch(NoSuchElementException e1){
                wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(), labelJobLocation);
            listOfLoc.add( labelJobLocation.getText());
            log.info("Location: "+ listOfLoc);

            return listOfLoc;
            }
         catch (Exception e) {
            log.error("Exception Found:" + e.getMessage());
        }
        return listOfLoc;
    }

    public void clickApplyNow(){
        ac= new Actions(driver);
        ac.scrollToElement(btnBottomApplyNow).build().perform();
        btnBottomApplyNow.click();
        wait.waitToGetTitle(driver, readConfig.getWaitTime(), "Workday");
    }

    public void clickOnCareerHomeLink(){
        wait.waitToElementToBeVisibleWithWebElement(driver, readConfig.getWaitTime(),lnkCareerHome );
        lnkCareerHome.click();
        wait.waitToGetTitle(driver, readConfig.getWaitTime(),"Careers at Labcorp | Embrace Possibilities, Change Lives");
        log.info("Current Page titel: " +driver.getTitle());
    }

}
