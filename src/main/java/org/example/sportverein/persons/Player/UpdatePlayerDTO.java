package org.example.sportverein.persons.Player;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.UUID;

public record UpdatePlayerDTO(
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
        UUID teamUuid,

        boolean isActive
) {

    public Player updatePlayer(Player player){
        player.setFirstName(firstName());
        player.setLastName(lastName());
        player.setBirthDate(birthDate());
        player.setPhoneNumber(PhoneNumber.fromString(phoneNumber()));
        player.setEmail(email());
        player.setPosition(position());
        player.setIsActive(isActive());
        return player;
    }

}
