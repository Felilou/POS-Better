package org.example.sportverein.persons.Staff;

import org.example.sportverein.Team.Team;
import org.example.sportverein.persons.Staff.Staff.Role;

import java.time.LocalDate;
import java.util.UUID;

public record StaffDTO(
    UUID uuid,
    String firstName,
    String lastName,
    String email,
    String phone,
    LocalDate birthDate,
    Role role,
    TeamSummaryDTO team
) {
    public record TeamSummaryDTO(UUID uuid, String name, boolean archived) {
        public static TeamSummaryDTO fromTeam(Team team) {
            if (team == null) return null;
            return new TeamSummaryDTO(team.getUuid(), team.getName(), team.isArchived());
        }
    }

    public static StaffDTO fromStaff(Staff staff) {
        if (staff == null) return null;

        return new StaffDTO(
            staff.getUuid(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getEmail(),
            staff.getPhoneNumber().toString(),
            staff.getBirthDate(),
            staff.getRole(),
            TeamSummaryDTO.fromTeam(staff.getTeam())
        );
    }
}
