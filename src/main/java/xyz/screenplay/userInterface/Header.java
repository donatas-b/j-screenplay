package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

public class Header {
    public static final Target BTN_HOME = Button.containingText("Home");
    public static final Target BTN_LOGOUT = Button.containingText("Logout");
    public static final Target LBL_HEADING = Target.the("Main heading text").locatedBy("mainHeading");
}
