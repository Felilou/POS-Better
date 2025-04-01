package org.example.sportverein.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findByUuid(UUID uuid);

}
