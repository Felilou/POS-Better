package org.example.sportverein.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UpdateTeamDTO(
        @Length(min=1, max=255)
        @NotEmpty
        @NotBlank
        @NotNull
        String name,

        boolean archived
) {
    public Team updateTeam(Team team) {
        team.setName(name());
        team.setArchived(archived());
        return team;
    }
}
