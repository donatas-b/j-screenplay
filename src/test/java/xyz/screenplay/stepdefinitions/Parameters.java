package xyz.screenplay.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import xyz.screenplay.CastOfUsers;

public class Parameters {
    @ParameterType(".*")
    public Actor actor(String actorType) {
        return OnStage.theActorCalled(actorType);
    }

    @Before
    public void setTheStage() {
        CastOfUsers users = new CastOfUsers();
        OnStage.setTheStage(users);
    }


}
