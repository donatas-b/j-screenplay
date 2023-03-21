package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import xyz.screenplay.userInterface.CustomerLoginPage;
import xyz.screenplay.userInterface.LoginPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginAsCustomer extends LoginAsCustomerBase implements Task {

    public static LoginAsCustomer login() {
        return instrumented(LoginAsCustomer.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(LoginPage.BTN_CUSTOMER_LOGIN),
                SelectFromOptions.byVisibleText(customer(actor).getUser().fullName()).from(CustomerLoginPage.DRP_YOUR_NAME),
                Click.on(CustomerLoginPage.BTN_LOGIN));
    }

}
