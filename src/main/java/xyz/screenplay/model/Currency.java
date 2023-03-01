package xyz.screenplay.model;

import lombok.Getter;

public enum Currency {
    DOLLAR("Dollar"),
    PUND("Pound"),
    RUPEE("Rupee");

    @Getter
    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public static Currency byValue(String currencyValue) {
        for (Currency currency : Currency.values()) {
            if (currency.currency.equals(currencyValue)) {
                return currency;
            }
        }
        throw new IllegalArgumentException(
                String.format("Currency enum does not contains value '%s'", currencyValue));
    }

}
