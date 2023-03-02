package xyz.screenplay.questions.customerList;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.pages.components.HtmlTable;
import xyz.screenplay.userInterface.CustomersPage;

import java.util.List;
import java.util.Map;

public class CustomerCount implements Question<Integer> {
    @Override
    public Integer answeredBy(Actor actor) {
        List<Map<Object, String>> table = HtmlTable.rowsFrom(CustomersPage.TBL_CUSTOMERS.resolveFor(actor));
        return table.size() - 1;
    }

}
