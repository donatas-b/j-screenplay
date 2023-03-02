package xyz.screenplay.model;

public enum SortOrder {
    ASC("Ascending"),
    DESC("Descending");

    private final String order;

    SortOrder(String order) {
        this.order = order;
    }

    public static SortOrder byValue(String orderValue) {
        for (SortOrder order : SortOrder.values()) {
            if (order.order.equals(orderValue)) {
                return order;
            }
        }
        throw new IllegalArgumentException(
                String.format("SortOrder enum does not contains value '%s'", orderValue));
    }

}
