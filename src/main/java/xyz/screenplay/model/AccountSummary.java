package xyz.screenplay.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@Builder
public class AccountSummary {
    private String accountNumber;
    private int balance;
    private Currency currency;

    private static final String NUMBER_LOCATOR = "Number : ";
    private static final String BALANCE_LOCATOR = "Balance : ";
    private static final String CURRENCY_LOCATOR = "Currency : ";

    public AccountSummary(String accountSummaryString) {
        int indexOfNumber = accountSummaryString.indexOf(NUMBER_LOCATOR);
        int indexOfBalance = accountSummaryString.indexOf(BALANCE_LOCATOR);
        int indexOfCurrency = accountSummaryString.indexOf(CURRENCY_LOCATOR);
        this.accountNumber = accountSummaryString.substring(indexOfNumber + NUMBER_LOCATOR.length(), indexOfBalance - 3);
        this.balance = Integer.parseInt(accountSummaryString.substring(indexOfBalance + BALANCE_LOCATOR.length(), indexOfCurrency - 3));
        this.currency = Currency.byValue(accountSummaryString.substring(indexOfCurrency + CURRENCY_LOCATOR.length()));
    }
}
