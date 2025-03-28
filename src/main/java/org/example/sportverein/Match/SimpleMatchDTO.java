package org.example.sportverein.Match;

import org.example.sportverein.Team.SimpleTeamDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record SimpleMatchDTO(
    Long id,
    UUID uuid,
    SimpleTeamDTO homeTeam,
    SimpleTeamDTO awayTeam,
    LocalDateTime kickOffTime
) {
    public static SimpleMatchDTO create(Match match) {
        if (match == null) return null;

        return new SimpleMatchDTO(
                match.getId(),
                match.getUuid(),
                SimpleTeamDTO.create(match.getHomeTeam()),
                SimpleTeamDTO.create(match.getAwayTeam()),
                match.getKickOffTime()
        );
    }
}
