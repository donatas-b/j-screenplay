package xyz.screenplay.questions.customerList;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.pages.components.HtmlTable;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.CustomersPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class CustomerListAsStrings implements Question<List<String>> {
    @Override
    public List<String> answeredBy(Actor actor) {
        List<String> result = new ArrayList<>();
        List<Map<Object, String>> table = HtmlTable.rowsFrom(CustomersPage.TBL_CUSTOMERS.resolveFor(actor));
        table.subList(1, table.size()).forEach(row -> result.add(new CustomerInformation(row).toString()));
        return result;
    }
}
