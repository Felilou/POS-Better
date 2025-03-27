package org.example.sportverein.persons;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber phoneNumber) {
        return phoneNumber == null ? null : phoneNumber.toString();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        return PhoneNumber.fromString(dbData);
    }

}
