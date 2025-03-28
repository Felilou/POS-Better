package org.example.sportverein.Match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.persons.Player.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchEventService {

    private final MatchEventRepository matchEventRepository;
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    public List<SimpleMatchEventDTO> getAllMatchEvents() {
        return matchEventRepository.findAll().stream()
                .map(SimpleMatchEventDTO::create)
                .collect(Collectors.toList());
    }

    public SimpleMatchEventDTO getMatchEventByUUID(UUID uuid) {
        return SimpleMatchEventDTO.create(matchEventRepository.findByUuid(uuid));
    }

    public List<SimpleMatchEventDTO> getMatchEventsByMatchUUID(UUID matchUuid) {
        Match match = matchRepository.findByUuid(matchUuid);
        return match.getEvents().stream()
                .map(SimpleMatchEventDTO::create)
                .collect(Collectors.toList());
    }

    @Transactional
    public SimpleMatchEventDTO createMatchEvent(CreateMatchEventDTO dto) {
        Match match = matchRepository.findByUuid(dto.matchUuid());
        MatchEvent event = new MatchEvent(match);
        event.setPlayer(playerRepository.findByUuid(dto.playerUuid()));
        event.setEventType(dto.eventType());
        event.setMinute(dto.minute());

        return SimpleMatchEventDTO.create(matchEventRepository.save(event));
    }

    @Transactional
    public SimpleMatchEventDTO updateMatchEvent(UUID uuid, UpdateMatchEventDTO dto) {
        MatchEvent event = matchEventRepository.findByUuid(uuid);
        dto.updateMatchEvent(event);

        event.setPlayer(playerRepository.findByUuid(dto.playerUUID()));

        return SimpleMatchEventDTO.create(matchEventRepository.save(event));
    }

    @Transactional
    public void deleteMatchEvent(UUID uuid) {
        matchEventRepository.delete(matchEventRepository.findByUuid(uuid));
    }
}
