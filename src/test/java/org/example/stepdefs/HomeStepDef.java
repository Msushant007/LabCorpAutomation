package org.example.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.HomePage;
import org.testng.Assert;
import org.example.utils.DriverSetup;
import org.example.utils.ReadConfig;

import java.io.IOException;

public class HomeStepDef extends BaseClass {


    HomePage objHomePg = new HomePage(driver);
    private static final Logger log = LogManager.getLogger("Home_Page");
    @Before
    public static void setUp() throws IOException {

        readConfig = new ReadConfig();
        driver = DriverSetup.getInstance().webDriverOpen(readConfig.getBrowser());
        driver.manage().window().maximize();


    }

    @Given("the user opens a browser and goes to Labcorp home page.")
    public void the_user_opens_a_browser_and_goes_to_labcorp_home_page() throws InterruptedException {
        log.info("Load Web Page." + readConfig.getBaseUrl());
        driver.get(readConfig.getBaseUrl());
        Thread.sleep(2000);
        objHomePg.acceptAllCookies();
    }

    @When("the user finds and clicks the {string} link")
    public void the_user_finds_and_clicks_the_link(String string) {
        objHomePg.clickTopLinks(string);
    }


    @Then("the user should be navigated to the Careers page")
    public void the_user_should_be_navigated_to_the_careers_page() {
        String getActualTitle=objHomePg.getCurrentPagePageTitle();
        Assert.assertEquals(getActualTitle, "Careers at Labcorp | Embrace Possibilities, Change Lives");

    }


}










