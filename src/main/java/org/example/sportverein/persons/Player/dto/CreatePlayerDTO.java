package org.example.sportverein.persons.Player.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.example.sportverein.persons.Player.Player;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record CreatePlayerDTO(

        @NotNull
        @Length(min = 1, max = 255)
        @NotEmpty
        @NotBlank
        String firstName,

        @NotNull
        @Length(min = 1, max = 255)
        @NotEmpty
        @NotBlank
        String lastName,

        @PastOrPresent
        @NotNull
        LocalDate birthDate,

        String phoneNumber,

        @Email
        @NotNull
        String email,

        @NotNull
        Player.Position position,

        @Nullable
        UUID teamUUID
) {
}
