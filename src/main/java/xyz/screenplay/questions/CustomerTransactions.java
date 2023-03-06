package xyz.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.pages.components.HtmlTable;
import xyz.screenplay.model.CustomerTransaction;
import xyz.screenplay.userInterface.CustomerTransactionsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerTransactions implements Question<List<CustomerTransaction>> {
    @Override
    public List<CustomerTransaction> answeredBy(Actor actor) {
        List<CustomerTransaction> result = new ArrayList<>();
        List<Map<Object, String>> table = HtmlTable.rowsFrom(CustomerTransactionsPage.TBL_TRANSACTIONS.resolveFor(actor).withTimeoutOf(Duration.ofSeconds(1)));
        table.subList(1, table.size()).forEach(row -> result.add(new CustomerTransaction(row)));
        return result;
    }

    public static Question<List<CustomerTransaction>> all() {
        return new CustomerTransactions();
    }
}
