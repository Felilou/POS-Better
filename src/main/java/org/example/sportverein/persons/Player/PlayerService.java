package org.example.sportverein.persons.Player;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.Team.Team;
import org.example.sportverein.Team.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public List<SimplePlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
                .map(SimplePlayerDTO::create)
                .collect(Collectors.toList());
    }

    public ComplexPlayerDTO getPlayerByUUID(UUID uuid) {
        return ComplexPlayerDTO.create(playerRepository.findByUuid(uuid));

    }

    @Transactional
    public ComplexPlayerDTO createPlayer(CreatePlayerDTO dto) {
        Player player = CreatePlayerDTO.toEntity(dto);

        if(dto.teamUuid() != null) {
            Team team = teamRepository.findByUuid(dto.teamUuid());
            player.setTeam(team);
        }

        return ComplexPlayerDTO.create(playerRepository.save(player));
    }

    @Transactional
    public ComplexPlayerDTO updatePlayer(UUID uuid, UpdatePlayerDTO dto) {
        Player player = playerRepository.findByUuid(uuid);
        dto.updatePlayer(player);

        if(dto.teamUuid() != null) {
            Team team = teamRepository.findByUuid(dto.teamUuid());
            player.setTeam(team);
        }

        return ComplexPlayerDTO.create(playerRepository.save(player));
    }

    @Transactional
    public void deletePlayer(UUID uuid) {
        playerRepository.delete(playerRepository.findByUuid(uuid));
    }

    public void setTeam(UUID playerUUID, UUID teamUUID) {
        Player player = playerRepository.findByUuid(playerUUID);
        Team team = teamRepository.findByUuid(teamUUID);
        player.setTeam(team);
        playerRepository.save(player);
    }
}
