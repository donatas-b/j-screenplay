package xyz.screenplay.questions;

import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.pages.components.HtmlTable;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.Customers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CustomerList implements Question<List<String>> {
    @Override
    public List<String> answeredBy(Actor actor) {
        List<String> result = new ArrayList<>();
        List<Map<Object, String>> table = HtmlTable.rowsFrom(Customers.TBL_CUSTOMERS.resolveFor(actor));
        table.forEach(row -> result.add(new CustomerInformation(row).toString()));
        return result;
    }

    public static Question<List<String>> allCustomers() {
        return new CustomerList();
    }
}
