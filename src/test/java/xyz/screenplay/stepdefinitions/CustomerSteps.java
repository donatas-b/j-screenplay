package xyz.screenplay.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import xyz.screenplay.model.AccountSummary;
import xyz.screenplay.model.Currency;
import xyz.screenplay.model.CustomerTransaction;
import xyz.screenplay.model.TransactionType;
import xyz.screenplay.questions.CustomerAccount;
import xyz.screenplay.questions.CustomerSuccess;
import xyz.screenplay.questions.CustomerTransactions;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.Login;
import xyz.screenplay.tasks.Navigate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CustomerSteps {

    @And("{actor} logs out")
    public void customerLogsOut(Actor actor) {
        actor.attemptsTo(Navigate.logout());
    }

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

    @And("{actor} withdraws {int} {string} from his {string} account")
    public void customerWithdrawsFromHisAccount(Actor actor, Integer amount, String currency, String accountNumber) {
        actor.attemptsTo(Customer.selectAccount(accountNumber));
        actor.attemptsTo(Customer.withdraw(amount));
    }

    @And("{actor} logs in again")
    public void heLogsInAgain(Actor actor) {
        actor.attemptsTo(Login.asCustomerAgain());
    }

    @When("{actor} Resets his {string} account")
    public void customerResetsHisAccount(Actor actor, String accountNumber) {
        actor.attemptsTo(Customer.selectAccount(accountNumber));
        actor.attemptsTo(Customer.resetAccount());
    }

    @Then("{actor} {string} Account Transactions should contain following records")
    public void hisAccountTransactionsShouldContainFollowingRecords(Actor actor, String accountNumber, DataTable expectedRecords) {
        actor.attemptsTo(Customer.selectAccount(accountNumber));
        actor.attemptsTo(Customer.transactions());

        List<CustomerTransaction> expectedTransactions = new ArrayList<>();
        List<Map<String, String>> rows = expectedRecords.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            expectedTransactions.add(CustomerTransaction.builder()
                    .amount(Integer.valueOf(columns.get("Amount")))
                    .transactionType(TransactionType.byValue(columns.get("TransactionType")))
                    .build());
        }
        List<String> actualTransactions = CustomerTransactions.all().answeredBy(actor).stream().map(CustomerTransaction::toStringNoDate).toList();
        ;
        expectedTransactions.forEach(expectedTransaction -> actor.attemptsTo(Ensure.that(actualTransactions).contains(expectedTransaction.toStringNoDate())));
    }
}
