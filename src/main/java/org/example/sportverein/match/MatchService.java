package org.example.sportverein.match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.team.Team;
import org.example.sportverein.team.TeamRepository;
import org.example.sportverein.UUIDRepository;
import org.example.sportverein.persons.player.Player;
import org.example.sportverein.persons.player.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchService extends AbstractService<Match> {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final MatchEventRepository matchEventRepository;
    private final TeamRepository teamRepository;

    @Override
    public UUIDRepository<Match> getUUIDRepository() {
        return matchRepository;
    }

    public List<Player> getAllHomePlayersOfMatch(UUID matchId) {
        Match match = matchRepository.findByUuid(matchId);
        return playerRepository.getAllByTeamHistoryAndTeamUUID(match.getHomeTeam().getUuid(), match.getKickOffTime());
    }

    public List<Player> getAllAwayPlayersOfMatch(UUID matchId) {
        Match match = matchRepository.findByUuid(matchId);
        return playerRepository.getAllByTeamHistoryAndTeamUUID(match.getAwayTeam().getUuid(), match.getKickOffTime());
    }

    public List<MatchEvent> getAllMatchEventsOfMatch(UUID matchId) {
        return matchEventRepository.getAllByMatchUuid(matchId);
    }

    public void setAwayTeam(UUID teamUUID, UUID matchUUID) {
        Match match = matchRepository.findByUuid(matchUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        match.setAwayTeam(team);
        matchRepository.save(match);
    }

    public void setHomeTeam(UUID teamUUID, UUID matchUUID) {
        Match match = matchRepository.findByUuid(matchUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        match.setHomeTeam(team);
        matchRepository.save(match);
    }

    public void addMatchEvent(UUID matchUUID, CreateMatchEventDTO matchEventDTO) {
        Match match = matchRepository.findByUuid(matchUUID);
        MatchEvent matchEvent = matchEventDTO.toEntity();
        matchEvent.setMatch(match);
        matchEvent.setPlayer(playerRepository.findByUUID(matchEventDTO.playerUUID()));
        matchEventRepository.save(matchEvent);
    }

    public void removeMatchEvent(UUID matchEventUUID) {
        MatchEvent matchEvent = matchEventRepository.findByUUID(matchEventUUID);
        matchEventRepository.delete(matchEvent);
    }

}
