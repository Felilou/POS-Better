package org.example.sportverein.persons.player;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.match.MatchEvent;
import org.example.sportverein.match.MatchEventRepository;
import org.example.sportverein.team.Team;
import org.example.sportverein.team.TeamRepository;
import org.example.sportverein.UUIDRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService extends AbstractService<Player> {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final MatchEventRepository matchEventRepository;

    @Override
    public UUIDRepository<Player> getUUIDRepository() {
        return playerRepository;
    }

    public void addPlayerToTeam(UUID playerUUID, UUID teamUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        player.setTeam(team);
        playerRepository.save(player);
    }

    public void removePlayerFromTeam(UUID playerUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        player.setTeam(null);
        playerRepository.save(player);
    }

    @Override
    public Player delete(UUID playerUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        player.setArchived(true);
        return playerRepository.save(player);
    }

    public List<MatchEvent> getMatchEventsOfPlayer(UUID playerUUID) {
        return matchEventRepository.getAllByPlayerUUID(playerUUID);
    }

}
