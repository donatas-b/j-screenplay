Feature: Bank Customer functionality

  As a Bank Customer
  I want to be able to manage my Accounts

  Scenario: Customer can see his Account balance
    Given Customer has logged in
    Then his "1007" Account Summary should have 0 "Dollar"
