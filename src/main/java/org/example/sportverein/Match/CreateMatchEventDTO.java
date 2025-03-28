package org.example.sportverein.Match;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateMatchEventDTO(
        @NotNull
        UUID playerUuid,

        @NotNull
        UUID matchUuid,

        @NotNull
        MatchEvent.EventType eventType,

        @Min(0)
        @Max(90)
        int minute
) {
}
