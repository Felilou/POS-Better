package org.example.sportverein.persons.Player;

import org.example.sportverein.Match.Match;
import org.example.sportverein.Match.MatchEvent;
import org.example.sportverein.Team.Team;
import org.example.sportverein.persons.Player.Player.Position;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record PlayerDTO(
    UUID uuid,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate birthDate,
    Position position,
    boolean archived,
    TeamSummaryDTO currentTeam,
    LocalDateTime joinedCurrentTeamAt,
    List<MatchEventSummaryDTO> matchEvents,
    Set<PlayerTeamMembershipSummaryDTO> teamMemberships
) {
    public record TeamSummaryDTO(UUID uuid, String name, boolean archived) {
        public static TeamSummaryDTO fromTeam(Team team) {
            if (team == null) return null;
            return new TeamSummaryDTO(team.getUuid(), team.getName(), team.isArchived());
        }
    }

    public record MatchSummaryDTO(UUID uuid, TeamSummaryDTO homeTeam, TeamSummaryDTO awayTeam, LocalDateTime kickOffTime) {
        public static MatchSummaryDTO fromMatch(Match match) {
            if (match == null) return null;
            return new MatchSummaryDTO(
                match.getUuid(),
                TeamSummaryDTO.fromTeam(match.getHomeTeam()),
                TeamSummaryDTO.fromTeam(match.getAwayTeam()),
                match.getKickOffTime()
            );
        }
    }

    public record MatchEventSummaryDTO(UUID uuid, MatchSummaryDTO match, String eventType, int minute) {
        public static MatchEventSummaryDTO fromMatchEvent(MatchEvent event) {
            if (event == null) return null;
            return new MatchEventSummaryDTO(
                event.getUuid(),
                MatchSummaryDTO.fromMatch(event.getMatch()),
                event.getEventType().description,
                event.getMinute()
            );
        }
    }

    public record PlayerTeamMembershipSummaryDTO(UUID uuid, TeamSummaryDTO team, LocalDateTime from, LocalDateTime to) {
        public static PlayerTeamMembershipSummaryDTO fromMembership(PlayerTeamMembership membership) {
            if (membership == null) return null;
            return new PlayerTeamMembershipSummaryDTO(
                membership.getUuid(),
                TeamSummaryDTO.fromTeam(membership.getTeam()),
                membership.getFrom(),
                membership.getTo()
            );
        }
    }

    public static PlayerDTO fromPlayer(Player player, List<MatchEvent> matchEvents) {
        if (player == null) return null;

        return new PlayerDTO(
            player.getUuid(),
            player.getFirstName(),
            player.getLastName(),
            player.getEmail(),
            player.getPhoneNumber().toString(),
            player.getBirthDate(),
            player.getPosition(),
            player.isArchived(),
            TeamSummaryDTO.fromTeam(player.getTeam()),
            player.getJoinedCurrentTeamAt(),

                matchEvents.stream()
                .map(MatchEventSummaryDTO::fromMatchEvent)
                .collect(Collectors.toList()),

                player.getTeamMemberships().stream()
                .map(PlayerTeamMembershipSummaryDTO::fromMembership)
                .collect(Collectors.toSet())

        );
    }
}
