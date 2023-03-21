package xyz.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import xyz.screenplay.abilities.AbleToLoginAsCustomer;

public class LoginAsCustomerBase {
    protected AbleToLoginAsCustomer customer(Actor actor) {
        return AbleToLoginAsCustomer.as(actor);
    }

}
