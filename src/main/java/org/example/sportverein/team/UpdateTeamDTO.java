package org.example.sportverein.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.sportverein.UpdateDTO;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record UpdateTeamDTO(
        UUID teamUUID,

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String name
) implements UpdateDTO<Team> {
        @Override
        public Team updateEntity(Team entity) {
                entity.setName(name);
                return entity;
        }
}
