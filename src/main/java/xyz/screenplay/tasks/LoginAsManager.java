package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import xyz.screenplay.abilities.AbleToLoginAsManager;
import xyz.screenplay.userInterface.LoginPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginAsManager implements Task {

    public static LoginAsManager login() {
        return instrumented(LoginAsManager.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        AbleToLoginAsManager.as(actor);
        actor.attemptsTo(Click.on(LoginPage.BTN_BANK_MANAGER_LOGIN));
    }

    private AbleToLoginAsManager manager(Actor actor) {
        return AbleToLoginAsManager.as(actor);
    }
}
