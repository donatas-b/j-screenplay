package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target BTN_CUSTOMER_LOGIN = Target.the("'Customer Login' button").locatedBy("//button[text()='Customer Login']");
    public static final Target BTN_BANK_MANAGER_LOGIN = Target.the("'Bank Manager Login' button").locatedBy("//button[text()='Bank Manager Login']");
}
