package org.example.sportverein.match;

import org.example.sportverein.UpdateDTO;

//this class has no use, it is just here because every entity needs an update DTO
public record UpdateMatchDTO() implements UpdateDTO<Match> {
    @Override
    public Match updateEntity(Match entity) {
        return null;
    }
}
