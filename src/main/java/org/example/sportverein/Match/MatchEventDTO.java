package org.example.sportverein.Match;

import org.example.sportverein.Match.MatchEvent.EventType;
import org.example.sportverein.Team.Team;
import org.example.sportverein.persons.Player.Player;

import java.time.LocalDateTime;
import java.util.UUID;

public record MatchEventDTO(
    UUID uuid,
    PlayerSummaryDTO player,
    MatchSummaryDTO match,
    EventType eventType,
    int minute
) {
    public record PlayerSummaryDTO(UUID uuid, String firstName, String lastName, TeamSummaryDTO team) {
        public static PlayerSummaryDTO fromPlayer(Player player) {
            if (player == null) return null;
            return new PlayerSummaryDTO(
                player.getUuid(),
                player.getFirstName(),
                player.getLastName(),
                TeamSummaryDTO.fromTeam(player.getTeam())
            );
        }
    }

    public record TeamSummaryDTO(UUID uuid, String name) {
        public static TeamSummaryDTO fromTeam(Team team) {
            if (team == null) return null;
            return new TeamSummaryDTO(team.getUuid(), team.getName());
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

    public static MatchEventDTO fromMatchEvent(MatchEvent event) {
        if (event == null) return null;

        return new MatchEventDTO(
            event.getUuid(),
            PlayerSummaryDTO.fromPlayer(event.getPlayer()),
            MatchSummaryDTO.fromMatch(event.getMatch()),
            event.getEventType(),
            event.getMinute()
        );
    }
}
