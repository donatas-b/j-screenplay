package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class AddCustomerPage {
    public static final Target INP_FIRST_NAME = Target.the("'First Name' input").locatedBy("//input[contains(@ng-model,'fName')]");
    public static final Target INP_LAST_NAME = Target.the("'Last Name' input").locatedBy("//input[contains(@ng-model,'lName')]");
    public static final Target INP_POST_CODE = Target.the("'Post Code' input").locatedBy("//input[contains(@ng-model,'postCd')]");
    public static final Target BTN_ADD_CUSTOMER = Target.the("'Add Customer' button").locatedBy("//button[text()='Add Customer']");

}
