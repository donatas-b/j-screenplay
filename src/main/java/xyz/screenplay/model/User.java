package xyz.screenplay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private UserType userType;

    public String fullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

}
