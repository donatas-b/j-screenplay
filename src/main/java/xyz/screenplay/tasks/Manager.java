package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import xyz.screenplay.userInterface.ManagerHomePage;

public class Manager {
    public static Task goToAddCustomer() {
        return Task.where("Add Customer", Click.on(ManagerHomePage.BTN_ADD_CUSTOMER));
    }

    public static Task goToOpenAccount() {
        return Task.where("Open Account", Click.on(ManagerHomePage.BTN_OPEN_ACCOUNT));
    }

    public static Task goToCustomers() {
        return Task.where("Customers", Click.on(ManagerHomePage.BTN_CUSTOMERS));
    }
}
