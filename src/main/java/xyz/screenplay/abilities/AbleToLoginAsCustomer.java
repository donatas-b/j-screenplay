package xyz.screenplay.abilities;

import lombok.Getter;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import xyz.screenplay.model.User;
import xyz.screenplay.model.UserType;

public class AbleToLoginAsCustomer implements Ability {
    @Getter
    private final User user;

    public static AbleToLoginAsCustomer with(String firstName, String lastName) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .userType(UserType.CUSTOMER)
                .build();
        return new AbleToLoginAsCustomer(user);
    }

    public static AbleToLoginAsCustomer as(Actor actor) {
        if (actor.abilityTo(AbleToLoginAsCustomer.class) == null) {
            throw new IllegalArgumentException(String.format("Actor '%s' is not AbleToLoginAsCustomer", actor.getName()));
        }
        return actor.abilityTo(AbleToLoginAsCustomer.class);
    }

    private AbleToLoginAsCustomer(User user) {
        this.user = user;
    }

    public String toString() {
        return "login as customer";
    }

}
