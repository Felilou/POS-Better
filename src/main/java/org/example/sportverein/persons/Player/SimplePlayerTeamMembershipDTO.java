package org.example.sportverein.persons.Player;

import org.example.sportverein.Team.SimpleTeamDTO;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public record SimplePlayerTeamMembershipDTO(
    Long id,
    UUID uuid,
    SimpleTeamDTO team,
    LocalDateTime from,
    LocalDateTime to,
    Period period
) {
    public static SimplePlayerTeamMembershipDTO create(PlayerTeamMembership membership) {
        if (membership == null) return null;

        return new SimplePlayerTeamMembershipDTO(
                membership.getId(),
                membership.getUuid(),
                SimpleTeamDTO.create(membership.getTeam()),
                membership.getFrom(),
                membership.getTo(),
                membership.getPeriod()
        );
    }
}
