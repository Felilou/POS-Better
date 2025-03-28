package org.example.sportverein.persons.Player;

import java.time.LocalDateTime;
import java.util.UUID;

public record SimplePlayerDTO(
    Long id,
    UUID uuid,
    String firstName,
    String lastName,
    boolean isActive,
    Player.Position position,
    LocalDateTime joinedCurrentTeamAt
) {
    public static SimplePlayerDTO create(Player player) {
        if (player == null) return null;

        return new SimplePlayerDTO(
                player.getId(),
                player.getUuid(),
                player.getFirstName(),
                player.getLastName(),
                player.isActive(),
                player.getPosition(),
                player.getJoinedCurrentTeamAt()
        );
    }
}
