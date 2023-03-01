package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;

public class OpenAccountPage {
    public static final Target DRP_CUSTOMER = Dropdown.withNameOrId("userSelect");
    public static final Target DRP_CURRENCY = Dropdown.withNameOrId("currency");
    public static final Target BTN_PROCESS = Button.containingText("Process");

}
