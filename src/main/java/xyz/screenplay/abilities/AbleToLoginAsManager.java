package xyz.screenplay.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class AbleToLoginAsManager implements Ability {
    public static AbleToLoginAsManager with() {
        return new AbleToLoginAsManager();
    }

    public static AbleToLoginAsManager as(Actor actor) {
        if (actor.abilityTo(AbleToLoginAsManager.class) == null) {
            throw new IllegalArgumentException(String.format("User '%s' is not AbleToLoginAsManager", actor.getName()));
        }
        return actor.abilityTo(AbleToLoginAsManager.class);
    }

    private AbleToLoginAsManager() {
    }

    private AbleToLoginAsManager manager(Actor actor) {
        return AbleToLoginAsManager.as(actor);
    }

    public String toString() {
        return "login as bank manager";
    }
}
