Feature: bank manager functionality

  As a bank Manager
  I want to be able to manage Customers

  Scenario: Manager should be able to add new Customer
    Given Manager has logged in
    When he enters new Customer data
    And he tries to save it
    Then Customer fields should be cleared
    And Customer should appear in Customers list