package org.example.sportverein.Team;

import java.util.UUID;

public record ShortTeamDTO(
        String name,
        UUID teamUuid
) {
    public static ShortTeamDTO fromTeam(Team team) {
        return new ShortTeamDTO(team.getName(), team.getUuid());
    }
}
