package org.example.sportverein.Match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.Team.Team;
import org.example.sportverein.Team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public List<SimpleMatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(SimpleMatchDTO::create)
                .collect(Collectors.toList());
    }

    public ComplexMatchDTO getMatchByUUID(UUID uuid) {
        return ComplexMatchDTO.create(matchRepository.findByUuid(uuid));
    }

    @Transactional
    public ComplexMatchDTO createMatch(CreateMatchDTO dto) {
        Team homeTeam = teamRepository.findByUuid(dto.homeTeamUuid());
        Team awayTeam = teamRepository.findByUuid(dto.awayTeamUuid());
        Match match = new Match(homeTeam, awayTeam, dto.kickOffTime());

        return ComplexMatchDTO.create(matchRepository.save(match));
    }

    @Transactional
    public void deleteMatch(UUID uuid) {
        matchRepository.delete(matchRepository.findByUuid(uuid));
    }
}
