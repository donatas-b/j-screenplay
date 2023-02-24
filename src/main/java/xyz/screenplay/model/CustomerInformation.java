package xyz.screenplay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CustomerInformation {
    private String firstName;
    private String lastName;
    private String postCode;
}
