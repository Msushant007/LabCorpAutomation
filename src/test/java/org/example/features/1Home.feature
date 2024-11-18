
Feature: Labcorp Careers Page Navigation
@Ui
  Scenario: User navigates to Careers page from Labcorp home page
    Given the user opens a browser and goes to Labcorp home page.
    When the user finds and clicks the "Careers" link
    Then the user should be navigated to the Careers page