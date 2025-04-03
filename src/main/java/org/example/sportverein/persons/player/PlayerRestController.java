package org.example.sportverein.persons.player;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.example.sportverein.AbstractService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping("/{uuid}/team")
    public void addPlayerToTeam(@PathVariable UUID uuid, @RequestBody UUID teamUUID) {
        playerService.addPlayerToTeam(uuid, teamUUID);
    }

    @DeleteMapping("/{uuid}/team")
    public void removePlayerFromTeam(@PathVariable UUID uuid) {
        playerService.removePlayerFromTeam(uuid);
    }

}
