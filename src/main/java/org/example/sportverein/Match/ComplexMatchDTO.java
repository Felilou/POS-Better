package org.example.sportverein.Match;

import org.example.sportverein.Team.SimpleTeamDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ComplexMatchDTO(
    Long id,
    UUID uuid,
    SimpleTeamDTO homeTeam,
    SimpleTeamDTO awayTeam,
    LocalDateTime kickOffTime,
    List<SimpleMatchEventDTO> events
) {
    public static ComplexMatchDTO create(Match match) {
        if (match == null) return null;

        return new ComplexMatchDTO(
                match.getId(),
                match.getUuid(),
                SimpleTeamDTO.create(match.getHomeTeam()),
                SimpleTeamDTO.create(match.getAwayTeam()),
                match.getKickOffTime(),
                match.getEvents().stream()
                        .map(SimpleMatchEventDTO::create)
                        .collect(Collectors.toList())
        );
    }
}
