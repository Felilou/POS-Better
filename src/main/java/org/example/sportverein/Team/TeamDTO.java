package org.example.sportverein.Team;

import org.example.sportverein.Match.Match;
import org.example.sportverein.Match.MatchEvent;
import org.example.sportverein.persons.Player.Player;
import org.example.sportverein.persons.Player.PlayerTeamMembership;
import org.example.sportverein.persons.Staff.Staff;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record TeamDTO(
    UUID uuid,
    String name,
    boolean archived,
    List<PlayerSummaryDTO> players,
    List<StaffSummaryDTO> staff,
    List<MatchSummaryDTO> matches,
    List<PlayerTeamMembershipSummaryDTO> playerHistory,
    List<MatchEventSummaryDTO> events
) {
    public record PlayerSummaryDTO(UUID uuid, String firstName, String lastName, String position, boolean isActive) {
        public static PlayerSummaryDTO fromPlayer(Player player) {
            if (player == null) return null;
            return new PlayerSummaryDTO(
                player.getUuid(),
                player.getFirstName(),
                player.getLastName(),
                player.getPosition() != null ? player.getPosition().longName : null,
                player.isArchived()
            );
        }
    }

    public record StaffSummaryDTO(UUID uuid, String firstName, String lastName, String role) {
        public static StaffSummaryDTO fromStaff(Staff staff) {
            if (staff == null) return null;
            return new StaffSummaryDTO(
                staff.getUuid(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getRole() != null ? staff.getRole().title : null
            );
        }
    }

    public record MatchSummaryDTO(UUID uuid, UUID homeTeamId, UUID awayTeamId, LocalDateTime kickOffTime) {
        public static MatchSummaryDTO fromMatch(Match match) {
            if (match == null) return null;
            return new MatchSummaryDTO(
                match.getUuid(),
                match.getHomeTeam().getUuid(),
                match.getAwayTeam().getUuid(),
                match.getKickOffTime()
            );
        }
    }

    public record PlayerTeamMembershipSummaryDTO(UUID uuid, UUID playerId, LocalDateTime from, LocalDateTime to) {
        public static PlayerTeamMembershipSummaryDTO fromMembership(PlayerTeamMembership membership) {
            if (membership == null) return null;
            return new PlayerTeamMembershipSummaryDTO(
                membership.getUuid(),
                membership.getPlayer().getUuid(),
                membership.getFrom(),
                membership.getTo()
            );
        }
    }

    public record MatchEventSummaryDTO(UUID uuid, UUID playerId, UUID matchId, String eventType, int minute) {
        public static MatchEventSummaryDTO fromMatchEvent(MatchEvent event) {
            if (event == null) return null;
            return new MatchEventSummaryDTO(
                event.getUuid(),
                event.getPlayer().getUuid(),
                event.getMatch().getUuid(),
                event.getEventType().description,
                event.getMinute()
            );
        }
    }

    public static TeamDTO fromTeam(Team team, List<Player> currentPlayers, List<Staff> currentStaff, List<Match> matches, List<PlayerTeamMembership> playerHistory, List<MatchEvent> events) {
        if (team == null) return null;

        return new TeamDTO(
            team.getUuid(),
            team.getName(),
            team.isArchived(),

                currentPlayers.stream()
                .map(PlayerSummaryDTO::fromPlayer)
                .collect(Collectors.toList()),

                currentStaff.stream()
                .map(StaffSummaryDTO::fromStaff)
                .collect(Collectors.toList()),

                matches.stream()
                .map(MatchSummaryDTO::fromMatch)
                .collect(Collectors.toList()),

                playerHistory.stream()
                .map(PlayerTeamMembershipSummaryDTO::fromMembership)
                .collect(Collectors.toList()),

                events.stream()
                .map(MatchEventSummaryDTO::fromMatchEvent)
                .collect(Collectors.toList())
        );
    }
}
