package xyz.screenplay.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class CustomerInformation {
    private String firstName;
    private String lastName;
    private String postCode;
    private List<String> accountNumbers;

    public CustomerInformation(Map<Object, String> tableRow) {
        this.firstName = tableRow.get(1);
        this.lastName = tableRow.get(2);
        this.postCode = tableRow.get(3);
        String accounts = tableRow.get(4);
        if (isEmpty(accounts)) {
            this.accountNumbers = null;
        } else {
            this.accountNumbers = Arrays.asList(tableRow.get(4).split(" "));
        }
    }

    public static CustomerInformation random() {
        String randomString = RandomStringUtils.randomAlphanumeric(5);
        return CustomerInformation.builder()
                .firstName(String.format("F%s", randomString))
                .lastName(String.format("L%s", randomString))
                .postCode(randomString)
                .build();
    }

    public String toStringShort() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public void addAccount(String accountNumber) {
        if (this.accountNumbers == null) {
            this.accountNumbers = new ArrayList<>();
        }
        this.accountNumbers.add(accountNumber);
    }
}
