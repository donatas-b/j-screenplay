Feature: Bank Manager functionality

  As a Bank Manager
  I want to be able to manage Customers

  Scenario: Manager should be able to add new Customer
    Given Manager has logged in
    When he enters new Customer data
    And he tries to save it
    Then Customer fields should be cleared
    And Customer should appear in Customers list


  Scenario: Manager can Open Account for Customer
    Given Manager has logged in
    And there is a Customer
    When Manager opens "Dollar" Account for Customer
    Then Customer Account should appear in Customers list