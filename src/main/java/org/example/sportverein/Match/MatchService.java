package org.example.sportverein.Match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.UUIDRepository;
import org.example.sportverein.persons.Player.Player;
import org.example.sportverein.persons.Player.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MatchService extends AbstractService<Match> {

    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final MatchEventRepository matchEventRepository;

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

}
