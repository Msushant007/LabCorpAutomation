package org.example.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.CareersPage;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class CareersStepDef  extends BaseClass{
    private static final Logger log = LogManager.getLogger("Careers_Page");
    CareersPage objCareerPg = new CareersPage(driver);
    public String jobTitleForSearch = "NA";



    @Given("I am on the job search page")
    public void i_am_on_the_job_search_page() {
        log.info("Job Search page: " + driver.getTitle());
    }

    @When("I search for job position")
    public void i_search_for_job_position() throws InterruptedException {
        jobTitleForSearch = objCareerPg.getFirstJobTileText();
        objCareerPg.searchJobWithJobTitle(jobTitleForSearch);

    }

    @Then("I should see a list of available positions that match the search")
    public void i_should_see_a_list_of_available_positions_that_match_the_search() {
        int getSerachJobCount = objCareerPg.getAllJobsFromSearchResult().size();
        Assert.assertNotEquals(getSerachJobCount, 0);
    }

    @When("I select the position from Search result")
    public void i_select_the_position_from_search_result() {
        objCareerPg.getAndSelectFirstJobsFromSearchResult(jobTitleForSearch);
    }


    @Then("the page should display the job title match with possition serached")
    public void the_page_should_display_the_job_title_match_with_possition_serached() {
        String actualJobTitle = objCareerPg.getTheJobDetailsForAssert("JobTitle");
        Assert.assertEquals(actualJobTitle, jobTitleForSearch);
    }

    @Then("the page should display the job Location")
    public void the_page_should_display_the_job_Location() {

        List<String> actualJobLocation = objCareerPg.getTheJobLocationForAssert();
        Assert.assertTrue(!actualJobLocation.isEmpty());
    }

    @And("the page should display the job Id")
    public void the_page_should_display_the_job_Id() {
        String actualJobId = objCareerPg.getTheJobDetailsForAssert("JobId");
        Assert.assertNotEquals(actualJobId, "NA");
    }

    @And("the job description text validation")
    public void the_job_description_text_validation() {

        Boolean flgMinQulFound = driver.findElement(By.xpath("//div[@class='job-description']//b[text()='Key Responsibilities:']//following::p[4]")).getText().contains("MUST be 18 years of age");
        Assert.assertTrue(flgMinQulFound, "Min Qualification Not Found.");
        Boolean flgKeyResp = driver.findElement(By.xpath("//div[@class='job-description']//b[text()='Key Responsibilities:']//following::p[1]")).getText().contentEquals("Provide appropriate health recommendations to participants as needed");
        Assert.assertTrue(flgKeyResp, "Key Responsibility Not Matched.");
        Boolean flgPhyReq = driver.findElement(By.xpath("//div[@class='job-description']//b[text()='Key Responsibilities:']//following::p[9]")).getText().equals("Must be able to lift to 15 pounds at times.");
        Assert.assertTrue(flgPhyReq, "Key Responsibility Not Matched.");
    }

    @Then("i click on click Apply Now button")
    public void i_click_on_click_apply_now_button() {
        objCareerPg.clickApplyNow();
        Assert.assertEquals(driver.getTitle(),"Workday");
    }

    @Then("I click to Return link")
    public void i_click_to_return_link() {
        objCareerPg.clickOnCareerHomeLink();
        Assert.assertEquals(driver.getTitle(),"Careers at Labcorp | Embrace Possibilities, Change Lives");


    }



}
