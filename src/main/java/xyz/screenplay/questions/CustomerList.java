package xyz.screenplay.questions;

import net.serenitybdd.screenplay.Question;
import xyz.screenplay.model.CustomerInformation;
import xyz.screenplay.questions.customerList.CustomerAccounts;
import xyz.screenplay.questions.customerList.CustomerCount;
import xyz.screenplay.questions.customerList.CustomerListAsObjects;
import xyz.screenplay.questions.customerList.CustomerListAsStrings;

import java.util.List;

public class CustomerList {
    public static Question<List<String>> asStrings() {
        return new CustomerListAsStrings();
    }

    public static Question<List<CustomerInformation>> asObjects() {
        return new CustomerListAsObjects();
    }

    public static Question<Integer> count() {
        return new CustomerCount();
    }

    public static Question<List<String>> accountsOf(CustomerInformation ofCustomer) {
        return new CustomerAccounts(ofCustomer);
    }

}
