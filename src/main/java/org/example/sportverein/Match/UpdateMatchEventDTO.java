package org.example.sportverein.Match;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateMatchEventDTO(
        @NotNull
        MatchEvent.EventType eventType,

        @Min(0)
        @Max(90)
        int minute,

        UUID playerUUID
) {
    public MatchEvent updateMatchEvent(MatchEvent event) {
        event.setEventType(eventType());
        event.setMinute(minute());
        return event;
    }
}
