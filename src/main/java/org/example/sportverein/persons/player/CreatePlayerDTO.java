package org.example.sportverein.persons.player;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.example.sportverein.CreateDTO;
import org.example.sportverein.persons.PhoneNumber;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreatePlayerDTO(

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
        Player.Position position
) implements CreateDTO<Player> {
        @Override
        public Player toEntity() {
                Player player = new Player();
                player.setFirstName(firstName);
                player.setLastName(lastName);
                player.setBirthDate(birthDate);
                player.setEmail(email);
                player.setPhoneNumber(PhoneNumber.fromString(phoneNumber));
                player.setArchived(false);
                player.setJoinedCurrentTeamAt(LocalDateTime.now());
                player.setPosition(position);
                return player;
        }
}
