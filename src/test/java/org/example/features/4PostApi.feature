Feature: POST Request to Sample API
  As a user
  I want to send a POST request to a sample API endpoint
  So that I can verify customer, payment, and product information in the response

  @Api
  Scenario: Validate POST request response for customer, payment, and product data

    Given the API default endpoint

    When I send a POST request to the endpoint with the payload

    Then The response status code should be 200

    And the response should include customer information:
      | name  | Jane Smith            |
      | email | janesmith@example.com |
      | phone | 1-987-654-3210        |
    And the response should include payment details:
      | method         | credit_card |
      | transaction_id | txn_67890   |
      | amount         | 111.97      |
      | currency       | USD         |
    And the response should include product1 information:
      | product_id | A101                |
      | name       | Wireless Headphones |
      | quantity   | 1                   |
      | price      | 79.99               |
    And the response should include product2 information:
      | product_id | B202            |
      | name       | Smartphone Case |
      | quantity   | 2               |
      | price      | 15.99           |