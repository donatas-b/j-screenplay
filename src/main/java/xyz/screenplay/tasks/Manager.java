package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.CustomersPage;
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

    public static Task searchCustomers(CustomerInformation searchCustomer) {
        return Task.where("Search Customers", Enter.theValue(searchCustomer.getFirstName()).into(CustomersPage.INP_SEARCH_CUSTOMER));
    }

    public static Task clearCustomerSearch() {
        return Task.where("Clear Customer Search", Clear.field(CustomersPage.INP_SEARCH_CUSTOMER));
    }

    public static Task deleteCustomer(CustomerInformation customerToDelete) {
        return Task.where("Delete Customer",
                Enter.theValue(customerToDelete.getFirstName()).into(CustomersPage.INP_SEARCH_CUSTOMER).then(
                        Click.on(CustomersPage.BTN_DELETE)
                ));
    }
}
