package xyz.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import xyz.screenplay.model.AccountSummary;
import xyz.screenplay.userInterface.CustomerHomePage;

public class CustomerAccount implements Question<AccountSummary> {
    @Override
    public AccountSummary answeredBy(Actor actor) {
        String accountSummaryString = Text.of(CustomerHomePage.TXT_ACCOUNT_SUMMARY).answeredBy(actor);
        return new AccountSummary(accountSummaryString);
    }

    public static Question<AccountSummary> summary() {
        return new CustomerAccount();
    }
}
