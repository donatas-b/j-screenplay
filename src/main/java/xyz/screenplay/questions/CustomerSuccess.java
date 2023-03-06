package xyz.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import xyz.screenplay.userInterface.CustomerHomePage;

public class CustomerSuccess implements Question<String> {
    @Override
    public String answeredBy(Actor actor) {
        return Text.of(CustomerHomePage.LBL_MESSAGE).answeredBy(actor);
    }

    public static Question<String> message() {
        return new CustomerSuccess();
    }
}
