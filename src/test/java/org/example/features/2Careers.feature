Feature: Job Position Search and Selection

  As a user, I want to search for active job positions on the site,
  so I can view and confirm details of available positions.

  @Ui
  Scenario: Search and view a Any Job position
    Given I am on the job search page
    When I search for job position
    Then I should see a list of available positions that match the search
    When I select the position from Search result
    And the page should display the job title match with possition serached
    And the page should display the job Location
    And the page should display the job Id
    Then i click on click Apply Now button
    Then I click to Return link
