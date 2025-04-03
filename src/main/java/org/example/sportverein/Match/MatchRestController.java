package org.example.sportverein.Match;

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


}
