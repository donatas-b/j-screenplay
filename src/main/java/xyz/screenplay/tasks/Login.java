package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import xyz.screenplay.userInterface.LoginPage;

public class Login {

    public static Task asCustomer() {
        return Task.where("Login as Customer", Click.on(LoginPage.BTN_CUSTOMER_LOGIN));
    }

    public static Task asBankManager() {
        return Task.where("Login as Bank Manager", Click.on(LoginPage.BTN_BANK_MANAGER_LOGIN));
    }
}
