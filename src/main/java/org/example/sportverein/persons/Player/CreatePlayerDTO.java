package org.example.sportverein.persons.Player;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record CreatePlayerDTO(

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
        Player.Position position,

        @Nullable
        UUID teamUuid
) {
    public static Player toEntity(CreatePlayerDTO dto) {
        Player player = new Player();
        player.setFirstName(dto.firstName());
        player.setLastName(dto.lastName());
        player.setBirthDate(dto.birthDate());
        player.setPhoneNumber(PhoneNumber.fromString(dto.phoneNumber()));
        player.setEmail(dto.email());
        player.setPosition(dto.position());
        return player;
    }
}
