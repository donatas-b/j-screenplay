package xyz.screenplay;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import org.apache.commons.collections.CollectionUtils;
import xyz.screenplay.abilities.AbleToLoginAsCustomer;
import xyz.screenplay.abilities.AbleToLoginAsManager;
import xyz.screenplay.model.UserType;

import java.util.List;

public class CastOfUsers extends Cast {

    @Override
    public Actor actorNamed(String userTypeString, Ability... abilities) {
        UserType userType = UserType.byValue(userTypeString);
        List<Ability> ableToBrowse = List.of(BrowseTheWeb.with(Serenity.getDriver()));
        CollectionUtils.addAll(ableToBrowse, abilities);
        Actor actor = super.actorNamed(userType.toString(), ableToBrowse.toArray(new Ability[0]));
        switch (userType) {
            case CUSTOMER -> actor.can(AbleToLoginAsCustomer.with("Ron", "Weasly"));
            case MANAGER -> actor.can(AbleToLoginAsManager.with());
        }
        return actor;
    }
}
