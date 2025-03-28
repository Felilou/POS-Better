package org.example.sportverein.Team;

import org.example.sportverein.Match.SimpleMatchDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ComplexTeamDTO(
    Long id,
    UUID uuid,
    String name,
    boolean archived,
    List<SimpleMatchDTO> matches
) {
    public static ComplexTeamDTO create(Team team) {
        if (team == null) return null;

        return new ComplexTeamDTO(
                team.getId(),
                team.getUuid(),
                team.getName(),
                team.isArchived(),
                team.getMatches().stream()
                        .map(SimpleMatchDTO::create)
                        .collect(Collectors.toList())
        );
    }
}
