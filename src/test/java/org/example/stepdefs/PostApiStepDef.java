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
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostApiStepDef extends BaseClass {
    private static final Logger log = LogManager.getLogger("Post Api ");
    JSONObject jsonObjectSaleOrderMap;
    private static Response response;
    private static String jsonResponseAsString;
    private static RequestSpecification request;
    private static JsonPath jsonPath;


    @Given("the API default endpoint")
    public void the_api_default_endpoint() {

        RestAssured.baseURI = readConfig.getEndpoint();
        RestAssured.basePath = "/sample-request";
        request = RestAssured.given().queryParam("author", "beeceptor").log().all();
    }

    @When("I send a POST request to the endpoint with the payload")
    public void i_send_a_post_request_to_the_endpoint_with_the_payload() {

        response = request.header("Content-Type", "application/json")
                .when().body(createOrder().toString()).log().all().post();
        log.info("Request BODY :" );
    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(Integer expStatusCode) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, expStatusCode);
    }

    @And("the response should include customer information:")
    public void the_response_should_include_customer_information(DataTable dataTable) {
        response.getBody().prettyPeek();
        jsonPath = response.jsonPath();
        String newSaleOrder = jsonPath.get("parsedBody.order_id");
        log.info("Order Code : " + newSaleOrder);
        Assert.assertEquals(jsonPath.get("parsedBody.customer.name"), dataTable.cell(0, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.customer.email"), dataTable.cell(1, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.customer.phone"), dataTable.cell(2, 1));

    }

    @And("the response should include payment details:")
    public void the_response_should_include_payment_details(DataTable dataTable) {
        Assert.assertEquals(jsonPath.get("parsedBody.payment.method"), dataTable.cell(0, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.payment.transaction_id"), dataTable.cell(1, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.payment.amount"), dataTable.cell(2, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.payment.currency"), dataTable.cell(3, 1));

    }

    @And("the response should include product1 information:")
    public void the_response_should_include_product1_information(DataTable dataTable) {
        Assert.assertEquals(jsonPath.get("parsedBody.items[0].product_id"), dataTable.cell(0, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[0].name"), dataTable.cell(1, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[0].quantity"), dataTable.cell(2, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[0].price"), dataTable.cell(3, 1));

    }

    @And("the response should include product2 information:")
    public void the_response_should_include_product2_information(DataTable dataTable) {
        Assert.assertEquals(jsonPath.get("parsedBody.items[1].product_id"), dataTable.cell(0, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[1].name"), dataTable.cell(1, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[1].quantity"), dataTable.cell(2, 1));
        Assert.assertEquals(jsonPath.get("parsedBody.items[1].price"), dataTable.cell(3, 1));

    }

    public JSONObject createOrder() {
        Map<String, Object> order = new HashMap<>();
        order.put("order_id", "12345");
        order.put("order_status", "processing");
        order.put("created_at", "2024-11-07T12:00:00Z");


        Map<String, String> customer = new HashMap<>();
        customer.put("name", "Jane Smith");
        customer.put("email", "janesmith@example.com");
        customer.put("phone", "1-987-654-3210");
        Map<String, String> customerAddress = new HashMap<>();
        customerAddress.put("street", "456 Oak Street");
        customerAddress.put("city", "Metropolis");
        customerAddress.put("state", "NY");
        customerAddress.put("zipcode", "10001");
        customerAddress.put("country", "USA");
        customer.put("address", customerAddress.toString());

        List<Map<String, String>> items = new ArrayList<>();
        Map<String, String> item1 = new HashMap<>();
        item1.put("product_id", "A101");
        item1.put("name", "Wireless Headphones");
        item1.put("quantity", "1");
        item1.put("price", "79.99");
        items.add(item1);

        Map<String, String> item2 = new HashMap<>();
        item2.put("product_id", "B202");
        item2.put("name", "Smartphone Case");
        item2.put("quantity", "2");
        item2.put("price", "15.99");
        items.add(item2);

        Map<String, String> payment = new HashMap<>();
        payment.put("method", "credit_card");
        payment.put("transaction_id", "txn_67890");
        payment.put("amount", "111.97");
        payment.put("currency", "USD");

        Map<String, String> shipping = new HashMap<>();
        shipping.put("method", "standard");
        shipping.put("cost", "5.99");
        shipping.put("estimated_delivery", "2024-11-15");

        order.put("customer", customer);
        order.put("items", items);
        order.put("payment", payment);
        order.put("shipping", shipping);
        return jsonObjectSaleOrderMap = new JSONObject(order);

    }

}