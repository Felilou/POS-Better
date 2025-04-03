package org.example.sportverein.team;

import org.example.sportverein.UUIDRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends UUIDRepository<Team> {
}
