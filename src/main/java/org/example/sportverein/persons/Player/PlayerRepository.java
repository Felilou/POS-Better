package org.example.sportverein.persons.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select p from Player p join Team t on t.uuid = p.team.uuid and t.uuid = :teamUuid")
    List<Player> getAllPlayersByTeam(UUID teamUuid);


}
