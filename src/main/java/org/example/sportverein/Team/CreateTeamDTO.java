package org.example.sportverein.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.sportverein.CreateDTO;
import org.hibernate.validator.constraints.Length;

public record CreateTeamDTO(
        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String name
) implements CreateDTO<Team> {
        @Override
        public Team toEntity() {
                Team team = new Team();
                team.setName(name);
                return team;
        }
}
