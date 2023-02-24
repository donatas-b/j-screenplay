package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;

public class Header {
    public static final Target BTN_HOME = Target.the("'Home' button").locatedBy("btn home");
    public static final Target BTN_LOGOUT = Target.the("'logout' button").locatedBy("btn logout");
    public static final Target LBL_HEADING = Target.the("Main heading text").locatedBy("mainHeading");
}
