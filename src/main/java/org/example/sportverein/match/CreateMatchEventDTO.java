package org.example.sportverein.match;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.sportverein.CreateDTO;

import java.util.UUID;

public record CreateMatchEventDTO(

        @NotNull
        UUID playerUUID,

        @NotNull
        MatchEvent.EventType eventType,

        @Min(0)
        @Max(90)
        int minute

) implements CreateDTO<MatchEvent> {

        @Override
        public MatchEvent toEntity() {
                MatchEvent matchEvent = new MatchEvent();
                matchEvent.setEventType(eventType);
                matchEvent.setMinute(minute);
                return matchEvent;
        }
}
