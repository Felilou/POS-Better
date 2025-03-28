package org.example.sportverein.persons.Staff;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record CreateStaffDTO(
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
    public static Staff toEntity(CreateStaffDTO dto) {
        Staff staff = new Staff();
        staff.setFirstName(dto.firstName());
        staff.setLastName(dto.lastName());
        staff.setBirthDate(dto.birthDate());
        staff.setPhoneNumber(PhoneNumber.fromString(dto.phoneNumber()));
        staff.setEmail(dto.email());
        staff.setRole(dto.role());
        return staff;
    }
}
