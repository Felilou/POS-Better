package org.example.sportverein.persons.player;

import jakarta.validation.constraints.*;
import org.example.sportverein.UpdateDTO;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record UpdatePlayerDTO(

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String firstName,

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String lastName,

        @PastOrPresent
        @NotNull
        LocalDate birthDate,

        @NotNull
        @NotEmpty
        @NotBlank
        String phoneNumber,

        @Email
        @NotNull
        String email,

        @NotNull
        Player.Position position
) implements UpdateDTO<Player> {
        @Override
        public Player updateEntity(Player entity) {
                entity.setFirstName(firstName);
                entity.setLastName(lastName);
                entity.setBirthDate(birthDate);
                entity.setEmail(email);
                entity.setPosition(position);
                entity.setPhoneNumber(PhoneNumber.fromString(phoneNumber));
                return entity;
        }
}
