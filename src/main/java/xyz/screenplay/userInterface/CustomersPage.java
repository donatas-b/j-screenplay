package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

import java.time.Duration;

public class CustomersPage {
    public static final Target INP_SEARCH_CUSTOMER = Target.the("'Search Customer' input").locatedBy("//input[contains(@ng-model,'searchCustomer')]");
    public static final Target TBL_CUSTOMERS = Target.the("Customers table").locatedBy("//table").waitingForNoMoreThan(Duration.ofSeconds(3));

}
