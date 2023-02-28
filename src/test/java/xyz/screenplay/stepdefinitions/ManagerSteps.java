package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.questions.CustomerFields;
import xyz.screenplay.questions.CustomerList;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.Login;
import xyz.screenplay.tasks.Manager;
import xyz.screenplay.tasks.Navigate;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

@Slf4j
public class ManagerSteps {

    private CustomerInformation customerInformation;

    @Given("{actor} has logged in")
    public void managerHasLoggedIn(Actor actor) {
        actor.attemptsTo(Navigate.toBankPage());
        actor.attemptsTo(Login.asBankManager());
        log.info("{} has logged in", actor.getName());
    }

    @When("{actor} enters new Customer data")
    public void managerEntersNewCustomerData(Actor actor) {
        actor.attemptsTo(Manager.addCustomer());
        log.info("{} is in Add Customer", actor.getName());
        String randomString = RandomStringUtils.randomAlphanumeric(5);
        customerInformation = CustomerInformation.builder()
                .firstName(String.format("F%s", randomString))
                .lastName(String.format("L%s", randomString))
                .postCode(randomString)
                .build();
        actor.attemptsTo(Customer.enterInformation(customerInformation));
        log.info("{} has entered customer info", actor.getName());
    }

    @And("{actor} tries to save it")
    public void triesToSaveIt(Actor actor) {
        actor.attemptsTo(Customer.addCustomer());
        WebDriver driver = Serenity.getDriver();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        log.info("Alert says: {}", alertMessage);
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
    public void customerShouldAppearInCustomersList() {
        theActorInTheSpotlight().attemptsTo(Manager.customers());
        theActorInTheSpotlight().should(seeThat(CustomerList.allCustomers(), hasItem(customerInformation.toString())));
        log.info("new Customer was saved");
    }
}
