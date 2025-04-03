package org.example.sportverein.persons.staff;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.example.sportverein.AbstractService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/staff")
public class StaffRestController extends AbstractRestController<Staff, CreateStaffDTO, UpdateStaffDTO, StaffDTO> {

    private final StaffService staffService;

    @Override
    public AbstractService<Staff> getService() {
        return staffService;
    }

    @Override
    public StaffDTO toDTO(Staff entity) {
        return StaffDTO.fromStaff(entity);
    }

    @PostMapping("/{uuid}/team")
    public void addStaffToTeam(@PathVariable UUID uuid, @RequestBody UUID teamUUID) {
        staffService.addStaffToTeam(uuid, teamUUID);
    }

    @DeleteMapping("/{uuid}/team")
    public void removeStaffFromTeam(@PathVariable UUID uuid) {
        staffService.removeTeamFromStaff(uuid);
    }

}
