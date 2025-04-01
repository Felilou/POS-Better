package org.example.sportverein.Team;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamRestController extends AbstractRestController<Team, CreateTeamDTO, UpdateTeamDTO, TeamService, TeamDTO> {

    private final TeamService teamService;

    @Override
    public TeamService getService() {
        return teamService;
    }

    @Override
    public TeamDTO toDTO(Team entity) {
        return TeamDTO.fromTeam(
                entity,
                teamService.getAllPlayersFromTeam(entity.getUuid()),
                teamService.getAllStaffFromTeam(entity.getUuid()),
                teamService.getAllMatchesFromTeam(entity.getUuid()),
                teamService.getAllPlayerTeamMembershipsFromTeam(entity.getUuid()),
                teamService.getAllEventsFromTeam(entity.getUuid())
        );
    }
}
