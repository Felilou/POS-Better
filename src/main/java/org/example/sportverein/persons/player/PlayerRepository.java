package org.example.sportverein.persons.player;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends UUIDRepository<Player> {

    @Query("select p from Player p join Team t on t.uuid = p.team.uuid and t.uuid = :teamUuid")
    List<Player> getAllPlayersByTeam(UUID teamUuid);

    @Query("SELECT p FROM Player p WHERE " +
            "(p.team.uuid = :teamUUID AND p.joinedCurrentTeamAt <= :at) OR " +
            "p.id IN (SELECT ptm.player.id FROM PlayerTeamMembership ptm WHERE " +
             "ptm.team.uuid = :teamUUID AND ptm.from <= :at AND ptm.to >= :at)")
    List<Player> getAllByTeamHistoryAndTeamUUID(UUID teamUUID, LocalDateTime at);

    @Query("SELECT p, COUNT(e) as goalCount FROM Player p JOIN MatchEvent e ON e.player.uuid = p.uuid " +
            "WHERE e.eventType = 'GOAL' GROUP BY p ORDER BY goalCount DESC LIMIT 1")
    Player findPlayersWithMostGoals();

    @Query("SELECT p, COUNT(e) as assistCount FROM Player p JOIN MatchEvent e ON e.player.uuid = p.uuid " +
            "WHERE e.eventType = 'ASSIST' GROUP BY p ORDER BY assistCount DESC LIMIT 1")
    Player findPlayersWithMostAssists();

    @Query("SELECT p, COUNT(e) as redCardCount FROM Player p JOIN MatchEvent e ON e.player.uuid = p.uuid " +
            "WHERE e.eventType = 'RED_CARD' GROUP BY p ORDER BY redCardCount DESC LIMIT 1")
    Player findPlayersWithMostRedCards();

}
