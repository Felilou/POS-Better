package org.example.sportverein.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchEventRepository extends JpaRepository<MatchEvent, Long> {

    @Query("select me from MatchEvent me join Player p on p.uuid = me.player.uuid where p.uuid = :matchUuid")
    public List<MatchEvent> getAllByPlayerUUID(UUID playerUuid);

    MatchEvent findByUuid(UUID uuid);
}
