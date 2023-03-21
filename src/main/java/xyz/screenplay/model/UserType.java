package xyz.screenplay.model;

import lombok.Getter;

public enum UserType {
    MANAGER("Manager"),
    CUSTOMER("Customer");

    @Getter
    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public static UserType byValue(String userType) {
        for (UserType type : UserType.values()) {
            if (type.type.equals(userType)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                String.format("UserType enum does not contains value '%s'", userType));
    }
}
