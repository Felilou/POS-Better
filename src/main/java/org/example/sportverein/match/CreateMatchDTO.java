package org.example.sportverein.match;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.example.sportverein.CreateDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateMatchDTO(

        @NotNull
        UUID homeTeamUuid,

        @NotNull
        UUID awayTeamUuid,

        @PastOrPresent
        LocalDateTime kickOffTime

) implements CreateDTO<Match> {

        @Override
        public Match toEntity() {
                Match match = new Match();
                match.setKickOffTime(kickOffTime);
                return match;
        }
}
