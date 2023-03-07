Feature: Bank Manager functionality

  As a Bank Manager
  I want to be able to manage Customers

  Background:
    Given Manager has logged in


  Scenario: Manager should be able to add new Customer
    When he enters new Customer data
    And he tries to save it
    Then Customer fields should be cleared
    And Customer should appear in Customer List


  Scenario: Manager can Open Account for Customer
    Given there is a Customer
    When Manager opens "Dollar" Account for Customer
    Then Customer Account should appear in Customer List


  Scenario: Manager can Search Customers
    Given there is a Customer
    When Manager does Search for Customer
    Then Customer should appear in Customer List
    And Customer List should contain 1 Customer


  Scenario Outline: Manager can Sort Customers
    Given there is a Customer
    When Manager Sorts Customer List by "<column>" in "<sortOrder>" order
    Then Customer list should be sorted by "<column>" in "<sortOrder>" order
#   delete created Customer - looks like for Scenario Outline browser is not restarted properly
    And Manager deletes the Customer
    Examples:
      | column     | sortOrder  |
      | First Name | Ascending  |
      | Post Code  | Descending |


  Scenario: Manager can Delete Customer
    Given there is a Customer
    And Customer appears in Customer List
    When Manager deletes the Customer
    Then Customer should no longer appear in Customer List
