package org.example.sportverein.persons.Staff;

import java.util.UUID;

public record SimpleStaffDTO(
    Long id,
    UUID uuid,
    String firstName,
    String lastName,
    Staff.Role role
) {
    public static SimpleStaffDTO create(Staff staff) {
        if (staff == null) return null;

        return new SimpleStaffDTO(
                staff.getId(),
                staff.getUuid(),
                staff.getFirstName(),
                staff.getLastName(),
                staff.getRole()
        );
    }
}
