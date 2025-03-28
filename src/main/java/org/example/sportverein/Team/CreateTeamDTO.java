package org.example.sportverein.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateTeamDTO(
        @Length(min=1, max=255)
        @NotEmpty
        @NotBlank
        @NotNull
        String name
) {
    public static Team toEntity(CreateTeamDTO dto) {
        Team team = new Team();
        team.setName(dto.name());
        team.setArchived(false);
        return team;
    }
}
