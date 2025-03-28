package org.example.sportverein.Match;

import org.example.sportverein.persons.Player.SimplePlayerDTO;

import java.util.UUID;

public record SimpleMatchEventDTO(
    Long id,
    UUID uuid,
    SimplePlayerDTO player,
    MatchEvent.EventType eventType,
    int minute
) {
    public static SimpleMatchEventDTO create(MatchEvent matchEvent) {
        if (matchEvent == null) return null;

        return new SimpleMatchEventDTO(
                matchEvent.getId(),
                matchEvent.getUuid(),
                SimplePlayerDTO.create(matchEvent.getPlayer()),
                matchEvent.getEventType(),
                matchEvent.getMinute()
        );
    }
}
