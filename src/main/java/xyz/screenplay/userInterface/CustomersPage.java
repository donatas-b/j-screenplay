package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class CustomersPage {
    public static final Target INP_SEARCH_CUSTOMER = Target.the("'Search Customer' input").locatedBy("//input[contains(@ng-model,'searchCustomer')]");
    public static final Target TBL_CUSTOMERS = Target.the("Customers table").locatedBy("//table");

}
