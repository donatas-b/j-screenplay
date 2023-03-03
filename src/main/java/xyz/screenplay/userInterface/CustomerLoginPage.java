package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;

public class CustomerLoginPage {
    public static final Target DRP_YOUR_NAME = Dropdown.withNameOrId("userSelect");
    public static final Target BTN_LOGIN = Button.containingText("Login");
}
