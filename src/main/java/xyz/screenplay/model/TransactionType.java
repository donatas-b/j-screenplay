package xyz.screenplay.model;

public enum TransactionType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    public static TransactionType byValue(String transactionType) {
        for (TransactionType type : TransactionType.values()) {
            if (type.type.equals(transactionType)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                String.format("TransactionType enum does not contains value '%s'", transactionType));
    }
}
