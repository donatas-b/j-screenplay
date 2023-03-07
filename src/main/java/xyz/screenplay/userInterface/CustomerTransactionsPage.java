package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Link;

public class CustomerTransactionsPage {
    public static final Target BTN_BACK = Button.containingText("Back");
    public static final Target BTN_RESET = Button.containingText("Reset");
    public static final Target TBL_TRANSACTIONS = Target.the("Transactions table").locatedBy("//table");
    public static final Target LNK_DATE = Link.containing("Date-Time");
}
