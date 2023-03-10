package xyz.screenplay.questions.customerList;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.thucydides.core.pages.components.HtmlTable;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.userInterface.CustomersPage;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CustomerAccounts implements Question<List<String>> {
    private final CustomerInformation ofCustomer;

    @Override
    public List<String> answeredBy(Actor actor) {
        List<Map<Object, String>> table = HtmlTable.rowsFrom(CustomersPage.TBL_CUSTOMERS.resolveFor(actor));
        for (Map<Object, String> row : table) {
            CustomerInformation customerRow = new CustomerInformation(row);
            if (customerRow.getFirstName().equals(ofCustomer.getFirstName()) && customerRow.getLastName().equals(ofCustomer.getLastName())) {
                return customerRow.getAccountNumbers();
            }
        }
        throw new IllegalArgumentException(String.format("Customer %s was not found", ofCustomer.toString()));
    }

}
