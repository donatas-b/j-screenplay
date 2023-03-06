package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import xyz.screenplay.model.AccountSummary;
import xyz.screenplay.model.Currency;
import xyz.screenplay.questions.CustomerAccount;
import xyz.screenplay.questions.CustomerSuccess;
import xyz.screenplay.tasks.Customer;

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
        theActorInTheSpotlight().attemptsTo(Ensure.that(actualSummary.toString()).isEqualTo(expectedSummary.toString()));
    }

    @When("{actor} deposits {int} {string} into his {string} account")
    public void customerDepositsIntoHisAccount(Actor actor, Integer amount, String currency, String accountNumber) {
        actor.attemptsTo(Customer.selectAccount(accountNumber));
        actor.attemptsTo(Customer.deposit(amount));
    }

    @Then("he should see success message {string}")
    public void heShouldSeeSuccessMessage(String message) {
        String actualMessage = CustomerSuccess.message().answeredBy(theActorInTheSpotlight());
        theActorInTheSpotlight().attemptsTo(Ensure.that(actualMessage).isEqualTo(message));
    }
}
