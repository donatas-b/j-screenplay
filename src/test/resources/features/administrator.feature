Feature: administrator functionality

  As a bank administrator
  I want to be able to manage Customers

  Scenario: Manager should be able to add new Customer
    Given Manager has logged in
    When he enters new Customer data
    And he tries to save it
    Then new Customer should be saved