package org.example.sportverein.persons;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

    @NonNull
    private String countryCode;

    @NonNull
    private String number;

    public static PhoneNumber fromString(String phoneStr) {
        if (phoneStr == null || phoneStr.isBlank()) {
            return null;
        }

        String countryCode;
        String number;

        if (phoneStr.startsWith("+")) {
            int codeEndIndex = Math.min(3, phoneStr.length());
            countryCode = phoneStr.substring(0, codeEndIndex);
            number = phoneStr.substring(codeEndIndex);
        } else if (phoneStr.startsWith("0")) {
            int codeEndIndex = Math.min(4, phoneStr.length());
            countryCode = "+" + phoneStr.substring(2, codeEndIndex);
            number = phoneStr.substring(codeEndIndex);
        } else {
            countryCode = "";
            number = phoneStr;
        }

        return new PhoneNumber(countryCode, number);
    }

    @Override
    public String toString() {
        return countryCode + number;
    }

    public String getFormattedNumber() {
        if (number == null || number.length() < 3) {
            return toString();
        }

        return countryCode + " " + number.substring(0, 3) + " " + number.substring(3);
    }

}