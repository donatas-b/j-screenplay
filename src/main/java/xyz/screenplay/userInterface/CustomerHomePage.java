package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;

public class CustomerHomePage {
    public static final Target LBL_CUSTOMER_NAME = Target.the("Customer Name").locatedBy("//span[contains(@class, 'fontBig')]");
    public static final Target DRP_ACCOUNTS = Dropdown.withNameOrId("accountSelect");
    public static final Target TXT_ACCOUNT_SUMMARY = Target.the("Account Summary").locatedBy("//div[contains(@class, 'center')]");
    public static final Target BTN_TRANSACTIONS = Button.containingText("Transactions");
    public static final Target BTN_DEPOSIT = Button.containingText("Deposit");
    public static final Target BTN_WITHDRAWAL = Button.containingText("Withdrawl");

}
