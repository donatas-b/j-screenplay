Feature: Bank Customer functionality

  As a Bank Customer
  I want to be able to manage my Accounts

  Background:
    Given Customer has logged in


  Scenario: Customer can see his Account balance
    Then his "1007" Account Summary should have 0 "Dollar"


  Scenario: Customer can Deposit money to his Account
    Given Customer deposits 213 "Dollar" into his "1007" account
    Then he should see success message "Deposit Successful"
    And his "1007" Account Summary should have 213 "Dollar"


  Scenario: Customer can Deposit money to his Account
    Given Customer deposits 213 "Rupee" into his "1009" account
    And he logs out
    And he logs in again
    When Customer withdraws 77 "Rupee" from his "1009" account
    Then he should see success message "Transaction successful"
    And his "1009" Account Summary should have 136 "Rupee"


  Scenario: Customer can Reset his Account
    Given Customer deposits 213 "Rupee" into his "1009" account
    And he logs out
    And he logs in again
    And Customer withdraws 77 "Rupee" from his "1009" account
    When Customer Resets his "1009" account
    Then his "1009" Account Summary should have 0 "Rupee"


  Scenario: Customer can Sort his Account Transactions
    Given Customer deposits 1213 "Pound" into his "1008" account
    And he logs out
    And he logs in again
    And Customer withdraws 177 "Pound" from his "1008" account
    And he logs out
    And he logs in again
    And Customer withdraws 217 "Pound" from his "1008" account
    When Customer Sorts his "1008" Account Transactions by Date in "Descending" order
    Then Customer Account Transactions should be sorted by Date in "Descending" order


  Scenario: Customer can check Transactions which happened in his Account
    Given Customer deposits 1213 "Pound" into his "1008" account
    And he logs out
    And he logs in again
    And Customer withdraws 177 "Pound" from his "1008" account
    And he logs out
    And he logs in again
    And Customer withdraws 217 "Pound" from his "1008" account
    Then Customer "1008" Account Transactions should contain following records
      | Amount | TransactionType |
      | 1213   | Credit          |
      | 177    | Debit           |
      | 217    | Debit           |
