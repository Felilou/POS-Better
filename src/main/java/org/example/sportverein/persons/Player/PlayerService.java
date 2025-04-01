package org.example.sportverein.persons.Player;

import org.example.sportverein.Match.MatchEvent;
import org.example.sportverein.Match.MatchEventRepository;
import org.example.sportverein.persons.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final MatchEventRepository matchEventRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, MatchEventRepository matchEventRepository) {
        this.playerRepository = playerRepository;
        this.matchEventRepository = matchEventRepository;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> {
                    List<MatchEvent> events = matchEventRepository.getAllByPlayerUUID(player.getUuid());
                    return PlayerDTO.fromPlayer(player, events);
                })
                .toList();
    }

    public PlayerDTO getPlayerById(UUID id) {
        Player player = playerRepository.findByUuid(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        List<MatchEvent> events = matchEventRepository.getAllByPlayerUUID(player.getUuid());

        return PlayerDTO.fromPlayer(player, events);
    }

    @Transactional
    public PlayerDTO createPlayer(CreatePlayerDTO createPlayerDTO) {
        Player player = new Player();
        player.setFirstName(createPlayerDTO.firstName());
        player.setLastName(createPlayerDTO.lastName());
        player.setBirthDate(createPlayerDTO.birthDate());
        player.setPhoneNumber(PhoneNumber.fromString(createPlayerDTO.phoneNumber()));
        player.setEmail(createPlayerDTO.email());
        player.setArchived(false);

        Player savedPlayer = playerRepository.save(player);

        return PlayerDTO.fromPlayer(savedPlayer, List.of());
    }

    @Transactional
    public PlayerDTO updatePlayer(UUID id, UpdatePlayerDTO updatePlayerDTO) {
        Player player = playerRepository.findByUuid(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        player.setFirstName(updatePlayerDTO.firstName());
        player.setLastName(updatePlayerDTO.lastName());
        player.setBirthDate(LocalDate.parse(updatePlayerDTO.birthDate()));
        player.setPhoneNumber(PhoneNumber.fromString(updatePlayerDTO.phoneNumber()));
        player.setEmail(updatePlayerDTO.email());

        Player updatedPlayer = playerRepository.save(player);

        List<MatchEvent> events = matchEventRepository.getAllByPlayerUUID(updatedPlayer.getUuid());

        return PlayerDTO.fromPlayer(updatedPlayer, events);
    }

    @Transactional
    public void deletePlayer(UUID id) {
        Player player = playerRepository.findByUuid(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        playerRepository.delete(player);
    }

    //TODO - Team setzen

}
