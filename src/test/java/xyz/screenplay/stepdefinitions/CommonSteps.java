package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import xyz.screenplay.tasks.Login;
import xyz.screenplay.tasks.Navigate;

@Slf4j
public class CommonSteps {

    @Given("{actor} has logged in")
    public void managerHasLoggedIn(Actor actor) {
        actor.attemptsTo(Navigate.toBankPage());
        if (actor.getName().equals("Manager")) {
            actor.attemptsTo(Login.asBankManager());
        } else if (actor.getName().equals("Customer")) {
            actor.attemptsTo(Login.asCustomer());
        } else {
            throw new IllegalArgumentException(String.format("Unkown Actor '%s'", actor.getName()));
        }
        log.info("{} has logged in", actor.getName());
    }

}
