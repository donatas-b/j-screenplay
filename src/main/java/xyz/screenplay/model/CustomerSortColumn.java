package xyz.screenplay.model;

public enum CustomerSortColumn {
    FIRST_NAME("First Name"),
    LAST_NAME("Last Name"),
    POST_CODE("Post Code");

    private final String column;

    CustomerSortColumn(String columnName) {
        this.column = columnName;
    }

    public static CustomerSortColumn byValue(String columnName) {
        for (CustomerSortColumn column : CustomerSortColumn.values()) {
            if (column.column.equals(columnName)) {
                return column;
            }
        }
        throw new IllegalArgumentException(
                String.format("CustomerSortColumn enum does not contains value '%s'", columnName));
    }

}
