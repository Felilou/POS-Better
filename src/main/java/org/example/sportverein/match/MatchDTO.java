package org.example.sportverein.match;

import org.example.sportverein.match.MatchEvent.EventType;
import org.example.sportverein.team.Team;
import org.example.sportverein.persons.player.Player;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record MatchDTO(
    UUID uuid,
    TeamSummaryDTO homeTeam,
    TeamSummaryDTO awayTeam,
    LocalDateTime kickOffTime,
    Set<MatchEventSummaryDTO> events,
    Set<PlayerSummaryDTO> homePlayer,
    Set<PlayerSummaryDTO> awayPlayers
) {
    public record TeamSummaryDTO(UUID uuid, String name) {
        public static TeamSummaryDTO fromTeam(Team team) {
            if (team == null) return null;
            return new TeamSummaryDTO(team.getUuid(), team.getName());
        }
    }

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

    public record MatchEventSummaryDTO(UUID uuid, PlayerSummaryDTO player, EventType eventType, int minute) {
        public static MatchEventSummaryDTO fromMatchEvent(MatchEvent event) {
            if (event == null) return null;
            return new MatchEventSummaryDTO(
                event.getUuid(),
                PlayerSummaryDTO.fromPlayer(event.getPlayer()),
                event.getEventType(),
                event.getMinute()
            );
        }
    }

    public static MatchDTO fromMatch(Match match, List<MatchEvent> events, List<Player> homePlayers, List<Player> awayPlayers) {
        if (match == null) return null;

        return new MatchDTO(
            match.getUuid(),
            TeamSummaryDTO.fromTeam(match.getHomeTeam()),
            TeamSummaryDTO.fromTeam(match.getAwayTeam()),
            match.getKickOffTime(),

                events.stream()
                .map(MatchEventSummaryDTO::fromMatchEvent)
                .collect(Collectors.toSet()),

                homePlayers.stream()
                .map(PlayerSummaryDTO::fromPlayer)
                .collect(Collectors.toSet()),

                awayPlayers.stream()
                .map(PlayerSummaryDTO::fromPlayer)
                .collect(Collectors.toSet())
        );
    }
}
