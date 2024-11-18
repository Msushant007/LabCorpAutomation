package org.example.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Map;
import java.util.TreeMap;

import static org.testng.Assert.assertEquals;

public class GetApiStepDef extends BaseClass {
    private static final Logger log = LogManager.getLogger("Get Api");

    private static Response response;
    private static String jsonResponseAsString;
    private static RequestSpecification request;
    private static JsonPath jsonPath;

    @Given("the API endpoint for sample-request")
    public void the_api_endpoint_for_sample_request() {
        RestAssured.baseURI = readConfig.getEndpoint();
        RestAssured.basePath = "/sample-request";
        request = RestAssured.given().queryParam("author", "beeceptor").log().all();

//echo.free.beeceptor.com/sample-request?author=beeceptor

    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {

        response = request
                .when().get().peek();

    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expStatusCode) {
        int actualStatusCode = response.statusCode();
        log.info("Response Code: " + actualStatusCode);
        assertEquals(actualStatusCode, expStatusCode);
    }

    @And("the response should include the path field")
    public void the_response_should_include_the_path_field() {
        String ExpPath = "/sample-request?author=beeceptor";
        jsonPath = response.jsonPath();
        String actualPath = jsonPath.get("path");
        log.info("Path: " + actualPath);
        assertEquals(actualPath, ExpPath);
    }

    @And("the response should include the ip field")
    public void the_response_should_include_the_ip_field() {
        String actualIp = jsonPath.get("ip");
        log.info("IP: " + actualIp);
        assertEquals(actualIp.substring(0, 13), "122.160.23.35");
    }

    @And("the response headers should include")
    public void the_response_headers_should_include(DataTable dataTable) {
        Map<String, String> expectedHeaders = new TreeMap<>();
        Map<String, String> actualHeaders = new TreeMap<>();
        String actualVal = "NA";
        for (int i = 0; i <= dataTable.cells().size() - 1; i++) {
            expectedHeaders.put(dataTable.cell(i, 0).toString(), dataTable.cell(i, 1).toString());
            String expheader = dataTable.cell(i, 0).toString();
            actualVal = jsonPath.get("headers.'" + expheader + "'").toString();
            actualHeaders.put(expheader, actualVal);

        }

        log.info("Expected Header Values : " + expectedHeaders);
        log.info("Actual Header Values : " + actualHeaders);
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

}


