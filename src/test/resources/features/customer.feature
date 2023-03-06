Feature: Bank Customer functionality

  As a Bank Customer
  I want to be able to manage my Accounts

  Scenario: Customer can see his Account balance
    Given Customer has logged in
    Then his "1007" Account Summary should have 0 "Dollar"


  Scenario: Customer can Deposit money to his Account
    Given Customer has logged in
    When Customer deposits 213 "Dollar" into his "1007" account
    Then he should see success message "Deposit Successful"
    And his "1007" Account Summary should have 213 "Dollar"
