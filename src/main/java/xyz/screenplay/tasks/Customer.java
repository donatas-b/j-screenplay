package xyz.screenplay.tasks;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.CustomerPage;

@Slf4j
public class Customer {
    public static Task enterInformation(CustomerInformation customer) {
        log.info("Entering Customer: {}", customer.toString());
        return Task.where("Enter Customer information",
                Enter.theValue(customer.getFirstName()).into(CustomerPage.INP_FIRST_NAME).then(
                        Enter.theValue(customer.getLastName()).into(CustomerPage.INP_LAST_NAME).then(
                                Enter.theValue(customer.getPostCode()).into(CustomerPage.INP_POST_CODE))));
    }

    public static Task addCustomer() {
        return Task.where("Save Customer information",
                Click.on(CustomerPage.BTN_ADD_CUSTOMER));
    }
}
