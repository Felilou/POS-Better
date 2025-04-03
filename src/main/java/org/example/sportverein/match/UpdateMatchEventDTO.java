package org.example.sportverein.match;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.sportverein.UpdateDTO;

import java.util.UUID;

public record UpdateMatchEventDTO(

        @NotNull
        MatchEvent.EventType eventType,

        @Min(0)
        @Max(90)
        int minute

) implements UpdateDTO<MatchEvent> {
        @Override
        public MatchEvent updateEntity(MatchEvent entity) {
                entity.setEventType(eventType);
                entity.setMinute(minute);
                return entity;
        }
}
