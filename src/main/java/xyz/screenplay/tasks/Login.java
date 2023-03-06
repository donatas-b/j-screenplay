package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import xyz.screenplay.userInterface.CustomerLoginPage;
import xyz.screenplay.userInterface.LoginPage;

public class Login {

    public static Task asCustomer() {
        return Task.where("Login as Customer", Click.on(LoginPage.BTN_CUSTOMER_LOGIN).then(
                SelectFromOptions.byVisibleText("Ron Weasly").from(CustomerLoginPage.DRP_YOUR_NAME).then(
                        Click.on(CustomerLoginPage.BTN_LOGIN)
                )));
    }

    public static Task asCustomerAgain() {
        return Task.where("Login as Customer again", SelectFromOptions.byVisibleText("Ron Weasly").from(CustomerLoginPage.DRP_YOUR_NAME).then(
                Click.on(CustomerLoginPage.BTN_LOGIN)
        ));
    }

    public static Task asBankManager() {
        return Task.where("Login as Bank Manager", Click.on(LoginPage.BTN_BANK_MANAGER_LOGIN));
    }
}
