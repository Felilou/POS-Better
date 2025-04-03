package org.example.sportverein.persons.Staff;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.example.sportverein.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //TODO add / remove Team
}
