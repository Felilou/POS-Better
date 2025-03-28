package org.example.sportverein.persons.Staff;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.Team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StaffService {

    private final StaffRepository staffRepository;
    private final TeamRepository teamRepository;

    public List<SimpleStaffDTO> getAllStaff() {
        return staffRepository.findAll().stream()
                .map(SimpleStaffDTO::create)
                .collect(Collectors.toList());
    }

    public ComplexStaffDTO getStaffByUUID(UUID uuid) {
        return ComplexStaffDTO.create(staffRepository.findByUuid(uuid));
    }

    @Transactional
    public ComplexStaffDTO createStaff(CreateStaffDTO dto) {
        Staff staff = CreateStaffDTO.toEntity(dto);

        if(dto.teamUuid() != null) {
            staff.setTeam(teamRepository.findByUuid(dto.teamUuid()));
        }

        return ComplexStaffDTO.create(staffRepository.save(staff));
    }

    @Transactional
    public ComplexStaffDTO updateStaff(UUID uuid, UpdateStaffDTO dto) {
        Staff staff = staffRepository.findByUuid(uuid);
        dto.updateStaff(staff);

        if(dto.teamUuid() != null) {
            staff.setTeam(teamRepository.findByUuid(dto.teamUuid()));
        }

        return ComplexStaffDTO.create(staffRepository.save(staff));
    }

    @Transactional
    public void deleteStaff(UUID uuid) {
        staffRepository.delete(staffRepository.findByUuid(uuid));
    }
}
