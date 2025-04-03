package org.example.sportverein.match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.UUIDRepository;
import org.example.sportverein.persons.player.Player;
import org.example.sportverein.persons.player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchEventService extends AbstractService<MatchEvent> {

    private final MatchEventRepository matchEventRepository;
    private final PlayerRepository playerRepository;

    @Override
    public UUIDRepository<MatchEvent> getUUIDRepository() {
        return matchEventRepository;
    }

    @Transactional
    public void setPlayerOfEvent(UUID playerUUID, UUID eventUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        MatchEvent matchEvent = matchEventRepository.findByUUID(eventUUID);
        matchEvent.setPlayer(player);
    }
}
