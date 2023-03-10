package xyz.screenplay.tasks;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.targets.Target;
import xyz.screenplay.model.Currency;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.model.SortOrder;
import xyz.screenplay.userInterface.*;

@Slf4j
public class Customer {
    public static Task enterInformation(CustomerInformation customer) {
        log.info("Entering Customer: {}", customer.toString());
        return Task.where("Enter Customer information",
                Enter.theValue(customer.getFirstName()).into(AddCustomerPage.INP_FIRST_NAME).then(
                        Enter.theValue(customer.getLastName()).into(AddCustomerPage.INP_LAST_NAME).then(
                                Enter.theValue(customer.getPostCode()).into(AddCustomerPage.INP_POST_CODE))));
    }

    public static Task addCustomer() {
        return Task.where("Save Customer information",
                Click.on(AddCustomerPage.BTN_ADD_CUSTOMER));
    }

    public static Task openAccount(CustomerInformation customer, Currency currency) {
        return Task.where("Adding Customer Account",
                SelectFromOptions.byVisibleText(customer.toStringShort()).from(OpenAccountPage.DRP_CUSTOMER).then(
                        SelectFromOptions.byVisibleText(currency.getCurrency()).from(OpenAccountPage.DRP_CURRENCY).then(
                                Click.on(OpenAccountPage.BTN_PROCESS)
                        )
                ));
    }

    public static Task selectAccount(String accountNumber) {
        return Task.where("Select Customer Account",
                SelectFromOptions.byVisibleText(accountNumber).from(CustomerHomePage.DRP_ACCOUNTS));
    }

    public static Task deposit(Integer amount) {
        return Task.where("Customer Deposits money into his Account",
                Click.on(CustomerHomePage.BTN_DEPOSIT).then(
                        Enter.theValue(amount.toString()).into(CustomerTransactionPage.INP_AMOUNT).then(
                                Click.on(CustomerTransactionPage.BTN_TRANSACTION)
                        )
                ));
    }

    public static Task withdraw(Integer amount) {
        return Task.where("Customer Withdraws money from his Account",
                Click.on(CustomerHomePage.BTN_WITHDRAWAL).then(
                        Enter.theValue(amount.toString()).into(CustomerTransactionPage.INP_AMOUNT).then(
                                Click.on(CustomerTransactionPage.BTN_TRANSACTION)
                        )
                ));
    }

    public static Task resetAccount() {
        return Task.where("Reset Customer Account",
                Click.on(CustomerHomePage.BTN_TRANSACTIONS).then(
                        Click.on(CustomerTransactionsPage.BTN_RESET).then(
                                Click.on((CustomerTransactionsPage.BTN_BACK))
                        )
                ));
    }

    public static Task transactions() {
        return Task.where("Go to Customer Transactions",
                Click.on(CustomerHomePage.BTN_TRANSACTIONS));
    }

    public static Task sortTransactions(SortOrder order) {
        Target sortColumn = CustomerTransactionsPage.LNK_DATE;
        switch (order) {
            case DESC -> {
                return Task.where("Sort Transactions", Click.on(sortColumn));
            }
            case ASC -> {
                return Task.where("Sort Transactions", Click.on(sortColumn).then(Click.on(sortColumn)));
            }
        }
        throw new IllegalArgumentException("Unknown SortOrder");

    }
}
