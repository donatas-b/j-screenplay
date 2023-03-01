package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import xyz.screenplay.model.Currency;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.questions.CustomerAccounts;
import xyz.screenplay.questions.CustomerCount;
import xyz.screenplay.questions.CustomerFields;
import xyz.screenplay.questions.CustomerList;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.Login;
import xyz.screenplay.tasks.Manager;
import xyz.screenplay.tasks.Navigate;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class ManagerSteps {

    private CustomerInformation currentCustomer;
    private String createdCustomerAccountNumber;

    private String getAlertMessage() {
        WebDriver driver = Serenity.getDriver();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        log.info("Alert says: {}", alertMessage);
        return alertMessage;
    }

    @Given("{actor} has logged in")
    public void managerHasLoggedIn(Actor actor) {
        actor.attemptsTo(Navigate.toBankPage());
        actor.attemptsTo(Login.asBankManager());
        log.info("{} has logged in", actor.getName());
    }

    @When("{actor} enters new Customer data")
    public void managerEntersNewCustomerData(Actor actor) {
        actor.attemptsTo(Manager.goToAddCustomer());
        log.info("{} is in Add Customer", actor.getName());
        currentCustomer = CustomerInformation.random();
        actor.attemptsTo(Customer.enterInformation(currentCustomer));
        log.info("{} has entered customer info", actor.getName());
    }

    @And("{actor} tries to save it")
    public void triesToSaveIt(Actor actor) {
        actor.attemptsTo(Customer.addCustomer());
        String alertMessage = getAlertMessage();
        log.info("Alert says: {}", getAlertMessage());
        //TODO: for some reason alert disappears when BrowserAlert.says() is called
//        actor.should(seeThat(BrowserAlert.says(Serenity.getDriver()), containsString("Customer added successfully with customer id")));
        assertThat(alertMessage, containsString("Customer added successfully with customer id"));
        log.info("{} saved new Customer", actor.getName());
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        theActorInTheSpotlight().should(seeThat(CustomerFields.areCleared()));
    }

    @And("Customer should appear in Customers list")
    @And("Customer appears in Customers list")
    public void customerShouldAppearInCustomersList() {
        theActorInTheSpotlight().attemptsTo(Manager.goToCustomers());
        theActorInTheSpotlight().should(seeThat(CustomerList.asStrings(), hasItem(currentCustomer.toString())));
        log.info("new Customer was saved");
    }

    @And("there is a Customer")
    public void thereIsACustomer() {
        theActorInTheSpotlight().attemptsTo(Manager.goToAddCustomer());
        currentCustomer = CustomerInformation.random();
        theActorInTheSpotlight().attemptsTo(Customer.enterInformation(currentCustomer));
        theActorInTheSpotlight().attemptsTo(Customer.addCustomer());
        log.info("new Customer was created");
    }

    @When("{actor} opens {string} Account for Customer")
    public void managerOpensAccountForCustomer(Actor actor, String currency) {
        actor.attemptsTo(Manager.goToOpenAccount());
        actor.attemptsTo(Customer.openAccount(currentCustomer, Currency.byValue(currency)));
        String alertMessage = getAlertMessage();
        createdCustomerAccountNumber = alertMessage.substring(alertMessage.indexOf(":") + 1);
        currentCustomer.addAccount(createdCustomerAccountNumber);
        assertThat(alertMessage, containsString("Account created successfully with account Number"));
        log.info("{} opened {} Account Number {} for {}", actor.getName(), currency, createdCustomerAccountNumber, currentCustomer.toString());
    }

    @Then("Customer Account should appear in Customers list")
    public void customerAccountShouldAppearInCustomersList() {
        theActorInTheSpotlight().attemptsTo(Manager.goToCustomers());
        theActorInTheSpotlight().should(seeThat(CustomerAccounts.all(currentCustomer), hasItem(createdCustomerAccountNumber)));
        log.info("Customer Account was saved");
    }

    @When("{actor} does Search for Customer")
    public void managerDoesSearchForCustomer(Actor actor) {
        actor.attemptsTo(Manager.goToCustomers());
        actor.attemptsTo(Manager.searchCustomers(currentCustomer));
        actor.should(seeThat(CustomerList.asStrings(), hasItem(currentCustomer.toString())));
    }

    @And("Customers list should contain {int} Customer")
    public void customersListShouldContainCustomer(int customerCount) {
        theActorInTheSpotlight().should(seeThat(CustomerCount.is(), comparesEqualTo(customerCount)));
    }

    @When("{actor} deletes the Customer")
    public void managerDeletesTheCustomer(Actor actor) {
        actor.attemptsTo(Manager.goToCustomers());
        actor.attemptsTo(Manager.deleteCustomer(currentCustomer));
    }

    @Then("Customer should no longer appear in Customers list")
    public void customerShouldNoLongerAppearInCustomersList() {
        theActorInTheSpotlight().attemptsTo(Manager.clearCustomerSearch());
        theActorInTheSpotlight().attemptsTo(Manager.searchCustomers(currentCustomer));
        theActorInTheSpotlight().should(seeThat(CustomerCount.is(), comparesEqualTo(0)));
    }
}
