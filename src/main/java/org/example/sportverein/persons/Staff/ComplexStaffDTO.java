package org.example.sportverein.persons.Staff;

import org.example.sportverein.Team.SimpleTeamDTO;

import java.util.UUID;

public record ComplexStaffDTO(
    Long id,
    UUID uuid,
    String firstName,
    String lastName,
    Staff.Role role,
    SimpleTeamDTO team
) {
    public static ComplexStaffDTO create(Staff staff) {
        if (staff == null) return null;

        return new ComplexStaffDTO(
                staff.getId(),
                staff.getUuid(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getRole(),
                SimpleTeamDTO.create(staff.getTeam())
        );
    }
}
