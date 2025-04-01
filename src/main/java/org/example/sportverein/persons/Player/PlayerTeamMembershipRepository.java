package org.example.sportverein.persons.Player;

import org.example.sportverein.UUIDRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerTeamMembershipRepository extends UUIDRepository<Player> {

    @Query("select mem from PlayerTeamMembership mem where mem.team.uuid = :teamUUID")
    List<PlayerTeamMembership> findAllByTeamUUID(UUID teamUUID);

}
