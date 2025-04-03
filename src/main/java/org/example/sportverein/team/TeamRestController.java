package org.example.sportverein.team;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractRestController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamRestController extends AbstractRestController<Team, CreateTeamDTO, UpdateTeamDTO, TeamDTO> {

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

    //TODO: Add / remove Player and Staff
    @PostMapping("/{uuid}/players")
    public void addPlayerToTeam(@PathVariable UUID uuid, @RequestBody UUID playerUUID) {
        teamService.addPlayer(playerUUID, uuid);
    }

    @DeleteMapping("/{uuid}/players")
    public void removePlayerFromTeam(@PathVariable UUID uuid, @RequestBody UUID playerUUID) {
        teamService.removePlayer(playerUUID, uuid);
    }

    @PostMapping("/{uuid}/staff")
    public void addStaffToTeam(@PathVariable UUID uuid, @RequestBody UUID staffUUID) {
        teamService.addStaff(staffUUID, uuid);
    }

    @DeleteMapping("/{uuid}/staff")
    public void removeStaffFromTeam(@PathVariable UUID uuid, @RequestBody UUID staffUUID) {
        teamService.removeStaff(staffUUID, uuid);
    }

}
