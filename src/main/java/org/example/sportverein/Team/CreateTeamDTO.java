package org.example.sportverein.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateTeamDTO(
        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String name
) {
}
