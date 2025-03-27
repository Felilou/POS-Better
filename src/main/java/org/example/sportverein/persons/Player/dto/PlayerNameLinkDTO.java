package org.example.sportverein.persons.Player.dto;

import org.example.sportverein.persons.Player.Player;

import java.util.UUID;

public record PlayerNameLinkDTO(
        UUID uuid,
        String firstName,
        String lastName
) {
    public static PlayerNameLinkDTO fromEntity(Player entity) {
        return new PlayerNameLinkDTO(entity.getUuid(), entity.getFirstName(), entity.getLastName());
    }
}
