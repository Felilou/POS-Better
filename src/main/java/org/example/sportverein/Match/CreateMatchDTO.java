package org.example.sportverein.Match;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateMatchDTO(
        @NotNull
        UUID homeTeamUuid,

        @NotNull
        UUID awayTeamUuid,

        @NotNull
        @Future
        LocalDateTime kickOffTime
) {
}
