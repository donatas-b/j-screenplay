package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import xyz.screenplay.userInterface.CustomerLoginPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginAsCustomerAgain extends LoginAsCustomerBase implements Task {

    public static LoginAsCustomerAgain login() {
        return instrumented(LoginAsCustomerAgain.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(SelectFromOptions.byVisibleText(customer(actor).getUser().fullName()).from(CustomerLoginPage.DRP_YOUR_NAME),
                Click.on(CustomerLoginPage.BTN_LOGIN));
    }

}
