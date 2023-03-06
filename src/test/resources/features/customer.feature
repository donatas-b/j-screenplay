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


  Scenario: Customer can Deposit money to his Account
    Given Customer has logged in
    And Customer deposits 213 "Rupee" into his "1009" account
    And he logs out
    And he logs in again
    When Customer withdraws 77 "Rupee" from his "1009" account
    Then he should see success message "Transaction successful"
    And his "1009" Account Summary should have 136 "Rupee"
