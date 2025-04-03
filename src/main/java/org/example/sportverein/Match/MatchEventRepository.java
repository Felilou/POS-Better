package org.example.sportverein.Match;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchEventRepository extends UUIDRepository<MatchEvent> {

    @Query("select me from MatchEvent me join Player p on p.uuid = me.player.uuid where p.uuid = :playerUuid")
    List<MatchEvent> getAllByPlayerUUID(UUID playerUuid);

    List<MatchEvent> getAllByMatchUuid(UUID matchUuid);

    @Query("select me from MatchEvent me join Player p on p.uuid = me.player.uuid where p.uuid = :playerUuid and me.eventType = :type")
    List<MatchEvent> getAllByPlayerUUIDAndEventType(UUID playerUuid, MatchEvent.EventType type);

    MatchEvent findByUuid(UUID uuid);
}
