package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.ensure.Ensure;
import xyz.screenplay.model.AccountSummary;
import xyz.screenplay.model.Currency;
import xyz.screenplay.questions.CustomerAccount;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CustomerSteps {
    @Then("his {string} Account Summary should have {int} {string}")
    public void hisAccountSummaryShouldHave(String accountNumber, int balance, String currency) {
        AccountSummary expectedSummary = AccountSummary.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .currency(Currency.byValue(currency))
                .build();
        AccountSummary actualSummary = CustomerAccount.summary().answeredBy(theActorInTheSpotlight());
        theActorInTheSpotlight().attemptsTo(Ensure.that(expectedSummary.toString()).isEqualTo(actualSummary.toString()));
    }
}
