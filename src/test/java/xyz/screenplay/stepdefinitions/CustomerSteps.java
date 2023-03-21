package xyz.screenplay.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import xyz.screenplay.model.*;
import xyz.screenplay.questions.CustomerAccount;
import xyz.screenplay.questions.CustomerSuccess;
import xyz.screenplay.questions.CustomerTransactions;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.LoginAsCustomerAgain;
import xyz.screenplay.tasks.Navigate;

import java.util.ArrayList;
import java.util.Comparator;
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
        actor.attemptsTo(LoginAsCustomerAgain.login());
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

        expectedTransactions.forEach(expectedTransaction -> actor.attemptsTo(Ensure.that(actualTransactions).contains(expectedTransaction.toStringNoDate())));
    }

    @When("{actor} Sorts his {string} Account Transactions by Date in {string} order")
    public void customerSortsHisAccountTransactionsByDateInOrder(Actor actor, String accountNumber, String sortOrder) {
        actor.attemptsTo(Customer.selectAccount(accountNumber));
        actor.attemptsTo(Customer.transactions());
        actor.attemptsTo(Customer.sortTransactions(SortOrder.byValue(sortOrder)));
    }

    @Then("{actor} Account Transactions should be sorted by Date in {string} order")
    public void customerAccountTransactionsShouldBeSortedByDateInOrder(Actor actor, String sortOrder) {
        List<CustomerTransaction> transactions = CustomerTransactions.all().answeredBy(actor);
        switch (SortOrder.byValue(sortOrder)) {
            case ASC -> transactions.sort(Comparator.comparing(CustomerTransaction::getDateTime));
            case DESC -> transactions.sort(Comparator.comparing(CustomerTransaction::getDateTime).reversed());
        }

        List<String> actualTransactions = CustomerTransactions.all().answeredBy(actor).stream().map(CustomerTransaction::toString).toList();
        List<String> expectedTransactions = transactions.stream().map(CustomerTransaction::toString).toList();

        theActorInTheSpotlight().attemptsTo(Ensure.that(actualTransactions).isEqualTo(expectedTransactions));
    }
}
