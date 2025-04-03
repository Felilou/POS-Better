package org.example.sportverein.persons.staff;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffRepository extends UUIDRepository<Staff> {

    @Query("select s from Staff s join Team t on t.uuid = s.team.uuid and t.uuid = :teamUuid")
    List<Staff> getAllStaffsByTeam(UUID teamUuid);

}
