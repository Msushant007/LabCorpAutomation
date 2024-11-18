
Feature: Request to Sample API Validation
@Api
  Scenario: Validate response from sample API
    Given the API endpoint for sample-request
    When I send a GET request to the endpoint
    Then the response status code should be 200
    And the response should include the path field
    And the response should include the ip field
    And the response headers should include
      | Host            | echo.free.beeceptor.com                 |
      | User-Agent      | Apache-HttpClient/4.5.13 (Java/17.0.10) |
      | Accept          | */*                                     |
      | Accept-Encoding | gzip,deflate                            |


