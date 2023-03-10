package xyz.screenplay.questions;

import lombok.NoArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import xyz.screenplay.userInterface.AddCustomerPage;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@NoArgsConstructor
public class CustomerFields implements Question<Boolean> {
    public static Question<Boolean> areCleared() {
        return new CustomerFields();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String firstName = AddCustomerPage.INP_FIRST_NAME.resolveFor(actor).getText();
        String lastName = AddCustomerPage.INP_LAST_NAME.resolveFor(actor).getText();
        String postCode = AddCustomerPage.INP_POST_CODE.resolveFor(actor).getText();
        return isEmpty(firstName) && isEmpty(lastName) && isEmpty(postCode);
    }
}
