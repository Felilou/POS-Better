package org.example.sportverein.match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.example.sportverein.AbstractService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchRestController extends AbstractRestController<Match, CreateMatchDTO, UpdateMatchDTO, MatchDTO> {

    private final MatchService matchService;

    @Override
    public AbstractService<Match> getService() {
        return matchService;
    }

    @Override
    public MatchDTO toDTO(Match entity) {
        return MatchDTO.fromMatch(
                entity,
                matchService.getAllMatchEventsOfMatch(entity.getUuid()),
                matchService.getAllHomePlayersOfMatch(entity.getUuid()),
                matchService.getAllAwayPlayersOfMatch(entity.getUuid())
        );
    }

    @PostMapping("/{uuid}/homeTeam")
    public void setHomeTeam(@PathVariable UUID uuid, @RequestBody UUID teamUUID) {
        matchService.setHomeTeam(teamUUID, uuid);
    }

    @PostMapping("/{uuid}/awayTeam")
    public void setAwayTeam(@PathVariable UUID uuid, @RequestBody UUID teamUUID) {
        matchService.setAwayTeam(teamUUID, uuid);
    }

    @PostMapping("/{uuid}/matchEvents")
    public void addMatchEvent(@PathVariable UUID uuid, @RequestBody CreateMatchEventDTO matchEventDTO) {
        matchService.addMatchEvent(uuid, matchEventDTO);
    }

}
