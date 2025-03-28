package org.example.sportverein.Team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;

    public List<SimpleTeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(SimpleTeamDTO::create)
                .collect(Collectors.toList());
    }

    public ComplexTeamDTO getTeamByUUID(UUID uuid) {
        return ComplexTeamDTO.create(teamRepository.findByUuid(uuid));
    }

    @Transactional
    public ComplexTeamDTO createTeam(CreateTeamDTO dto) {
        Team team = CreateTeamDTO.toEntity(dto);
        return ComplexTeamDTO.create(teamRepository.save(team));
    }

    @Transactional
    public ComplexTeamDTO updateTeam(UUID uuid, UpdateTeamDTO dto) {
        Team team = teamRepository.findByUuid(uuid);
        dto.updateTeam(team);
        return ComplexTeamDTO.create(teamRepository.save(team));
    }

    @Transactional
    public void archiveTeam(UUID uuid) {
        Team team = teamRepository.findByUuid(uuid);
        team.setArchived(true);
        teamRepository.save(team);
    }
}
