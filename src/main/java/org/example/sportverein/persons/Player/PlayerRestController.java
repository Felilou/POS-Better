package org.example.sportverein.persons.Player;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.example.sportverein.AbstractService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerRestController extends AbstractRestController<Player, CreatePlayerDTO, UpdatePlayerDTO, PlayerDTO> {

    private final PlayerService playerService;

    @Override
    public AbstractService<Player> getService() {
        return playerService;
    }

    @Override
    public PlayerDTO toDTO(Player entity) {
        return PlayerDTO.fromPlayer(entity, playerService.getMatchEventsOfPlayer(entity.getUuid()));
    }
}
