package org.example.sportverein.persons.Staff;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateStaffDTO(

        @NotNull
        UUID staffUUID,

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String firstName,

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String lastName,

        @PastOrPresent
        LocalDate birthDate,


        String phoneNumber,

        @Email
        String email,

        @NotNull
        Staff.Role role
) {
}
