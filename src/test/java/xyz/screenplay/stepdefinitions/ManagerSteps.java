package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import org.apache.commons.lang3.RandomStringUtils;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.tasks.Customer;
import xyz.screenplay.tasks.Login;
import xyz.screenplay.tasks.Manager;
import xyz.screenplay.tasks.Navigate;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

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
        log.info("{} saved new Customer", actor.getName());
    }

    @Then("new Customer should be saved")
    public void newCustomerShouldBeSaved() throws InterruptedException {
        theActorInTheSpotlight().attemptsTo(Manager.customers());
        Thread.sleep(10000);
        log.info("new Customer should be saved");
    }

}
