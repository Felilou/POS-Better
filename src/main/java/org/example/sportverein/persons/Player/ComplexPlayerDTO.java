package org.example.sportverein.persons.Player;

import org.example.sportverein.Team.SimpleTeamDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record ComplexPlayerDTO(
    Long id,
    UUID uuid,
    String firstName,
    String lastName,
    boolean isActive,
    Player.Position position,
    LocalDateTime joinedCurrentTeamAt,
    SimpleTeamDTO team,
    Set<SimplePlayerTeamMembershipDTO> teamMemberships
) {
    public static ComplexPlayerDTO create(Player player) {
        if (player == null) return null;

        return new ComplexPlayerDTO(
                player.getId(),
                player.getUuid(),
                player.getFirstName(),
                player.getLastName(),
                player.isActive(),
                player.getPosition(),
                player.getJoinedCurrentTeamAt(),
                SimpleTeamDTO.create(player.getTeam()),
                player.getTeamMemberships().stream()
                        .map(SimplePlayerTeamMembershipDTO::create)
                        .collect(Collectors.toSet())
        );
    }
}
