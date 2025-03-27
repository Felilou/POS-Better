package org.example.sportverein.persons.Staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("select s from Staff s join Team t on t.uuid = s.team.uuid and t.uuid = :teamUuid")
    List<Staff> getAllStaffsByTeam(UUID teamUuid);

}
