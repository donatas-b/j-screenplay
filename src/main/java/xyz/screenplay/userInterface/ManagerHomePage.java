package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class ManagerHomePage {
    public static final Target BTN_ADD_CUSTOMER = Target.the("'Add Customer' button").locatedBy("//button[contains(@ng-class,'btnClass1')]");
    public static final Target BTN_OPEN_ACCOUNT = Target.the("'Open Account' button").locatedBy("//button[contains(@ng-class,'btnClass2')]");
    public static final Target BTN_CUSTOMERS = Target.the("'Customers' button").locatedBy("//button[contains(@ng-class,'btnClass3')]");
}
