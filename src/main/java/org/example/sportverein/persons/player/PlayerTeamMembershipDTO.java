package org.example.sportverein.persons.player;

import org.example.sportverein.team.Team;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public record PlayerTeamMembershipDTO(
    UUID uuid,
    PlayerSummaryDTO player,
    TeamSummaryDTO team,
    LocalDateTime from,
    LocalDateTime to,
    Period period
) {
    public record PlayerSummaryDTO(UUID uuid, String firstName, String lastName, String position) {
        public static PlayerSummaryDTO fromPlayer(Player player) {
            if (player == null) return null;
            return new PlayerSummaryDTO(
                player.getUuid(),
                player.getFirstName(),
                player.getLastName(),
                player.getPosition() != null ? player.getPosition().longName : null
            );
        }
    }

    public record TeamSummaryDTO(UUID uuid, String name, boolean archived) {
        public static TeamSummaryDTO fromTeam(Team team) {
            if (team == null) return null;
            return new TeamSummaryDTO(team.getUuid(), team.getName(), team.isArchived());
        }
    }

    public static PlayerTeamMembershipDTO fromMembership(PlayerTeamMembership membership) {
        if (membership == null) return null;

        return new PlayerTeamMembershipDTO(
            membership.getUuid(),
            PlayerSummaryDTO.fromPlayer(membership.getPlayer()),
            TeamSummaryDTO.fromTeam(membership.getTeam()),
            membership.getFrom(),
            membership.getTo(),
            membership.getPeriod()
        );
    }
}
