package org.example.sportverein.Team;

import java.util.UUID;

public record SimpleTeamDTO(
    Long id,
    UUID uuid,
    String name,
    boolean archived
) {
    public static SimpleTeamDTO create(Team team) {
        if (team == null) return null;

        return new SimpleTeamDTO(
                team.getId(),
                team.getUuid(),
                team.getName(),
                team.isArchived()
        );
    }
}
