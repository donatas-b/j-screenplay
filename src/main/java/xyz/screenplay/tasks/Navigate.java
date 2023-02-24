package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class Navigate {
    public static Performable toBankPage() {
        return Task.where("{0} navigates to bank page",
                Open.browserOn().thePageNamed("home.page")
        );
    }
}
