package org.example.sportverein.match;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends UUIDRepository<Match> {

    Match findByUuid(UUID uuid);

    @Query("select m from Match m where m.awayTeam.uuid = :teamUUID or m.homeTeam.uuid = :teamUUID")
    List<Match> findAllByTeamUUID(UUID teamUUID);
}
