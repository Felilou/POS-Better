package org.example.sportverein.persons.staff;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.team.Team;
import org.example.sportverein.team.TeamRepository;
import org.example.sportverein.UUIDRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StaffService extends AbstractService<Staff> {

    private final StaffRepository staffRepository;
    private final TeamRepository teamRepository;


    @Override
    public UUIDRepository<Staff> getUUIDRepository() {
        return staffRepository;
    }

    public void addStaffToTeam(UUID staffUUID, UUID teamUUID) {
        Staff staff = staffRepository.findByUUID(staffUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        staff.setTeam(team);
        staffRepository.save(staff);
    }

    public void removeTeamFromStaff(UUID staffUUID) {
        Staff staff = staffRepository.findByUUID(staffUUID);
        staff.setTeam(null);
        staffRepository.save(staff);
    }

}
