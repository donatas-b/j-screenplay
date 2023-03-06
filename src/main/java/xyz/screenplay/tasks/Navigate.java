package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import xyz.screenplay.userInterface.Header;

public class Navigate {
    public static Task toBankPage() {
        return Task.where("{0} navigates to bank page",
                Open.browserOn().thePageNamed("home.page")
        );
    }

    public static Task logout() {
        return Task.where("customer logs out",
                Click.on(Header.BTN_LOGOUT));
    }

}
