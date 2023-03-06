package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

import java.time.Duration;

public class CustomerDepositPage {
    public static final Target INP_AMOUNT = Target.the("Amount to be Deposited")
            .locatedBy("//input[contains(@ng-model, 'amount')]")
            .waitingForNoMoreThan(Duration.ofSeconds(3));
    public static final Target BTN_DEPOSIT = Button.locatedBy("//button[contains(@class, 'btn-default')]");
}
