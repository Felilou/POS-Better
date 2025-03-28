package org.example.sportverein.persons.Staff;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateStaffDTO(
        @Length(min=1, max=255)
        @NotEmpty
        @NotBlank
        @NotNull
        String firstName,

        @Length(min=1, max=255)
        @NotEmpty
        @NotBlank
        @NotNull
        String lastName,

        @NotNull
        @PastOrPresent
        LocalDate birthDate,

        String phoneNumber,

        @Email
        String email,

        @NotNull
        Staff.Role role,

        @Nullable
        UUID teamUuid
) {
    public Staff updateStaff(Staff staff) {
        staff.setFirstName(firstName());
        staff.setLastName(lastName());
        staff.setBirthDate(birthDate());
        staff.setPhoneNumber(PhoneNumber.fromString(phoneNumber()));
        staff.setEmail(email());
        staff.setRole(role());
        return staff;
    }
}
