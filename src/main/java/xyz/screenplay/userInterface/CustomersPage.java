package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Link;

import java.time.Duration;

public class CustomersPage {
    public static final Target INP_SEARCH_CUSTOMER = Target.the("'Search Customer' input").locatedBy("//input[contains(@ng-model,'searchCustomer')]");
    public static final Target TBL_CUSTOMERS = Target.the("Customers table").locatedBy("//table").waitingForNoMoreThan(Duration.ofSeconds(3));
    public static final Target BTN_DELETE = Button.containingText("Delete");
    public static final Target LNK_FIRST_NAME = Link.containing("First Name");
    public static final Target LNK_LAST_NAME = Link.containing("Last Name");
    public static final Target LNK_POST_CODE = Link.containing("Post Code");

}
