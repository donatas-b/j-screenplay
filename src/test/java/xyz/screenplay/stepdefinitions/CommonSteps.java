package xyz.screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import xyz.screenplay.model.UserType;
import xyz.screenplay.tasks.LoginAsCustomer;
import xyz.screenplay.tasks.LoginAsManager;
import xyz.screenplay.tasks.Navigate;

@Slf4j
public class CommonSteps {

    @Given("{word} has logged in")
    public void managerHasLoggedIn(String userTypeString) {
        UserType userType = UserType.byValue(userTypeString);
        Actor user = OnStage.theActorCalled(userType.getType());
        user.attemptsTo(Navigate.toBankPage());
        if (userType == UserType.MANAGER) {
            user.attemptsTo(LoginAsManager.login());
        } else if (userType == UserType.CUSTOMER) {
            user.attemptsTo(LoginAsCustomer.login());
        } else {
            throw new IllegalArgumentException(String.format("Unkown User Type '%s'", userTypeString));
        }
        log.info("{} has logged in", user.getName());
    }

}
