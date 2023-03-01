package xyz.screenplay.tasks;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import xyz.screenplay.model.Currency;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.AddCustomerPage;
import xyz.screenplay.userInterface.OpenAccountPage;

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
        return Task.where("Adding Customer Acount",
                SelectFromOptions.byVisibleText(customer.toStringShort()).from(OpenAccountPage.DRP_CUSTOMER).then(
                        SelectFromOptions.byVisibleText(currency.getCurrency()).from(OpenAccountPage.DRP_CURRENCY).then(
                                Click.on(OpenAccountPage.BTN_PROCESS)
                        )
                ));
    }
}
