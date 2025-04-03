package org.example.sportverein.persons.staff;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.example.sportverein.CreateDTO;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateStaffDTO(

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
) implements CreateDTO<Staff> {
        @Override
        public Staff toEntity() {
                Staff staff = new Staff();
                staff.setFirstName(firstName);
                staff.setLastName(lastName);
                staff.setBirthDate(birthDate);
                staff.setRole(role);
                staff.setEmail(email);
                staff.setPhoneNumber(PhoneNumber.fromString(phoneNumber));
                return staff;
        }
}
