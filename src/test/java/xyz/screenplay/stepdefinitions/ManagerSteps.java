package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import xyz.screenplay.model.Currency;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.model.CustomerSortColumn;
import xyz.screenplay.model.SortOrder;
import xyz.screenplay.questions.CustomerFields;
import xyz.screenplay.questions.CustomerList;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.Manager;

import java.util.Comparator;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

@Slf4j
public class ManagerSteps {

    private static final String CURRENT_CUSTOMER = "currentCustomer";
    private static final String CUSTOMER_ACCOUNT_NUMBER = "createdCustomerAccountNumber";

    private String getAlertMessage() {
        WebDriver driver = Serenity.getDriver();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        log.info("Alert says: {}", alertMessage);
        return alertMessage;
    }

    @When("{actor} enters new Customer data")
    public void managerEntersNewCustomerData(Actor actor) {
        enterRandomCustomerInformation(actor);
        log.info("{} has entered customer info", actor.getName());
    }

    @And("{actor} tries to save it")
    public void triesToSaveIt(Actor actor) {
        actor.attemptsTo(Customer.addCustomer());
        String alertMessage = getAlertMessage();
        log.info("Alert says: {}", alertMessage);
        //TODO: for some reason alert disappears when BrowserAlert.says() is called
//        actor.should(seeThat(BrowserAlert.says(Serenity.getDriver()), containsString("Customer added successfully with customer id")));
        actor.attemptsTo(Ensure.that(alertMessage).contains("Customer added successfully with customer id"));
        log.info("{} saved new Customer", actor.getName());
    }

    @Then("Customer fields should be cleared")
    public void customerFieldsShouldBeCleared() {
        theActorInTheSpotlight().attemptsTo(Ensure.thatTheAnswerTo(CustomerFields.areCleared()).isTrue());
    }

    @And("Customer should appear in Customer List")
    @And("Customer appears in Customer List")
    public void customerShouldAppearInCustomerList() {
        theActorInTheSpotlight().attemptsTo(Manager.goToCustomers());
        theActorInTheSpotlight().should(seeThat(CustomerList.asStrings(), hasItem(theActorInTheSpotlight().recall(CURRENT_CUSTOMER).toString())));
        log.info("new Customer was saved");
    }

    @And("there is a Customer")
    public void thereIsACustomer() {
        enterRandomCustomerInformation(theActorInTheSpotlight());
        theActorInTheSpotlight().attemptsTo(Customer.addCustomer());
        log.info("new Customer was created");
    }

    private void enterRandomCustomerInformation(Actor actor) {
        actor.attemptsTo(Manager.goToAddCustomer());
        actor.remember(CURRENT_CUSTOMER, CustomerInformation.random());
        actor.attemptsTo(Customer.enterInformation(actor.recall(CURRENT_CUSTOMER)));
    }

    @When("{actor} opens {string} Account for Customer")
    public void managerOpensAccountForCustomer(Actor actor, String currency) {
        actor.attemptsTo(Manager.goToOpenAccount());
        actor.attemptsTo(Customer.openAccount(actor.recall(CURRENT_CUSTOMER), Currency.byValue(currency)));
        String alertMessage = getAlertMessage();
        actor.remember(CUSTOMER_ACCOUNT_NUMBER, alertMessage.substring(alertMessage.indexOf(":") + 1));
        CustomerInformation customerInformation = actor.recall(CURRENT_CUSTOMER);
        customerInformation.addAccount(actor.recall(CUSTOMER_ACCOUNT_NUMBER));
        actor.remember(CURRENT_CUSTOMER, customerInformation);
        assertThat(alertMessage, containsString("Account created successfully with account Number"));
        log.info("{} opened {} Account Number {} for {}", actor.getName(), currency, actor.recall(CUSTOMER_ACCOUNT_NUMBER), actor.recall(CURRENT_CUSTOMER).toString());
    }

    @Then("Customer Account should appear in Customer List")
    public void customerAccountShouldAppearInCustomersList() {
        theActorInTheSpotlight().attemptsTo(Manager.goToCustomers());
        String createdAccountNumber = theActorInTheSpotlight().recall(CUSTOMER_ACCOUNT_NUMBER);
        CustomerInformation customerInformation = theActorInTheSpotlight().recall(CURRENT_CUSTOMER);
        theActorInTheSpotlight().should(seeThat(CustomerList.accountsOf(customerInformation), hasItem(createdAccountNumber)));
        log.info("Customer Account was saved");
    }

    @When("{actor} does Search for Customer")
    public void managerDoesSearchForCustomer(Actor actor) {
        actor.attemptsTo(Manager.goToCustomers());
        actor.attemptsTo(Manager.searchCustomers(actor.recall(CURRENT_CUSTOMER)));
        actor.should(seeThat(CustomerList.asStrings(), hasItem(actor.recall(CURRENT_CUSTOMER).toString())));
    }

    @And("Customer List should contain {int} Customer")
    public void customerListShouldContainCustomer(int customerCount) {
        theActorInTheSpotlight().should(seeThat(CustomerList.count(), comparesEqualTo(customerCount)));
    }

    @When("{actor} deletes the Customer")
    public void managerDeletesTheCustomer(Actor actor) {
        actor.attemptsTo(Manager.goToCustomers());
        actor.attemptsTo(Manager.deleteCustomer(actor.recall(CURRENT_CUSTOMER)));
    }

    @Then("Customer should no longer appear in Customer List")
    public void customerShouldNoLongerAppearInCustomerList() {
        theActorInTheSpotlight().attemptsTo(Manager.clearCustomerSearch());
        theActorInTheSpotlight().attemptsTo(Manager.searchCustomers(theActorInTheSpotlight().recall(CURRENT_CUSTOMER)));
        theActorInTheSpotlight().attemptsTo(Ensure.that(CustomerList.count()).isEqualTo(0));
    }

    @When("{actor} Sorts Customer List by {string} in {string} order")
    public void managerSortsCustomerListByInOrder(Actor actor, String sortColumnName, String sortOrder) {
        actor.attemptsTo(Manager.goToCustomers());
        actor.attemptsTo(Manager.sortCustomers(CustomerSortColumn.byValue(sortColumnName), SortOrder.byValue(sortOrder)));
    }

    @Then("Customer list should be sorted by {string} in {string} order")
    public void customerListShouldBeSortedByInOrder(String sortColumnName, String sortOrder) {
        List<CustomerInformation> actualCustomerList = CustomerList.asObjects().answeredBy(theActorInTheSpotlight());
        log.info("before sort: {}", actualCustomerList);
        switch (SortOrder.byValue(sortOrder)) {
            case ASC -> {
                switch (CustomerSortColumn.byValue(sortColumnName)) {
                    case FIRST_NAME -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getFirstName));
                    case LAST_NAME -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getLastName));
                    case POST_CODE -> actualCustomerList.sort(Comparator.comparing(CustomerInformation::getPostCode));
                }
            }
            case DESC -> {
                switch (CustomerSortColumn.byValue(sortColumnName)) {
                    case FIRST_NAME ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getFirstName).reversed());
                    case LAST_NAME ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getLastName).reversed());
                    case POST_CODE ->
                            actualCustomerList.sort(Comparator.comparing(CustomerInformation::getPostCode).reversed());
                }
            }
        }
        log.info("after sort: {}", actualCustomerList);

        List<String> actualCustomerListStrings = CustomerList.asStrings().answeredBy(theActorInTheSpotlight());
        log.info("actualCustomerListStrings: {}", actualCustomerListStrings);
        List<String> expectedCustomerListStrings = actualCustomerList.stream().map(CustomerInformation::toString).toList();
        log.info("expectedCustomerListStrings: {}", expectedCustomerListStrings);

        theActorInTheSpotlight().attemptsTo(Ensure.that(expectedCustomerListStrings).isEqualTo(actualCustomerListStrings));
    }

}
