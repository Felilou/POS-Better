package org.example.sportverein.Team;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends UUIDRepository<Team> {
}
