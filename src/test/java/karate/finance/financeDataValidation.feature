Feature: Finance Data Validation

  Background:
    * url baseUrl
    * def initializePath = '/api/finance/initialize'
    * def fetchTextBasedOnIndexPath = '/api/finance/'

  Scenario: Initialize Finance Data File

    Given path initializePath
    And param size = '625'
    When method GET
    Then status 200
    And match $ == "File Successfully Generated"

  Scenario: Validate fetched response string with Straight Matching Text

    Given path fetchTextBasedOnIndexPath + '200'
    When method GET
    Then status 200
    And match $ contains "finance"
    
  Scenario: Validate fetched response string with Regex Pattern

    Given path fetchTextBasedOnIndexPath + '400'
    And param regexRequired = 'true'
    When method GET
    Then status 200
    And match response == "Regex Match Found Successfully"
    
  Scenario: Validate fetched response string with Text Sample Company
  
  	Given path fetchTextBasedOnIndexPath + '-1'
    When method GET
    Then status 200
    And match $ contains "Sample Company"