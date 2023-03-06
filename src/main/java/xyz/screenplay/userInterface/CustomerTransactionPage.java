package xyz.screenplay.userInterface;

import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;

import java.time.Duration;

public class CustomerTransactionPage {
    public static final Target INP_AMOUNT = Target.the("Transaction Amount")
            .locatedBy("//input[contains(@ng-model, 'amount')]")
            .waitingForNoMoreThan(Duration.ofSeconds(3));
    public static final Target BTN_TRANSACTION = Button.locatedBy("//button[contains(@class, 'btn-default')]");
}
