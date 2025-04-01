package org.example.sportverein.persons.Staff;

import org.example.sportverein.AbstractService;
import org.example.sportverein.UUIDRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService extends AbstractService<Staff> {
    @Override
    public UUIDRepository<Staff> getUUIDRepository() {
        return null;
    }
}
