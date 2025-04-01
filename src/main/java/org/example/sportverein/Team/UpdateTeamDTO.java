package org.example.sportverein.Team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record UpdateTeamDTO(
        UUID teamUUID,

        @NotNull
        @Length(min=3, max=20)
        @NotBlank
        String name
) {
}
