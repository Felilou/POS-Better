package org.example.sportverein.persons.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p from Player p join Team t on t.uuid = p.team.uuid and t.uuid = :teamUuid")
    List<Player> getAllPlayersByTeam(UUID teamUuid);

    @Query("SELECT p FROM Player p WHERE " +
            "(p.team.uuid = :teamUUID AND p.joinedCurrentTeamAt <= :at) OR " +
            "p.id IN (SELECT ptm.player.id FROM PlayerTeamMembership ptm WHERE " +
            "ptm.team.uuid = :teamUUID AND ptm.from <= :at AND ptm.to >= :at)")
    List<Player> getAllByTeamHistoryAndTeamUUID(UUID teamUUID, LocalDateTime at);

    Optional<Player> findByUuid(UUID id);
}
