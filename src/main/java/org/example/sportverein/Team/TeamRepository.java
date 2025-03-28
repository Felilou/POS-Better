package org.example.sportverein.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUuid(UUID teamUuid);
}
