package org.example.sportverein.team;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.match.Match;
import org.example.sportverein.match.MatchEvent;
import org.example.sportverein.match.MatchEventRepository;
import org.example.sportverein.match.MatchRepository;
import org.example.sportverein.UUIDRepository;
import org.example.sportverein.persons.player.Player;
import org.example.sportverein.persons.player.PlayerRepository;
import org.example.sportverein.persons.player.PlayerTeamMembership;
import org.example.sportverein.persons.player.PlayerTeamMembershipRepository;
import org.example.sportverein.persons.staff.Staff;
import org.example.sportverein.persons.staff.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamService extends AbstractService<Team> {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final StaffRepository staffRepository;
    private final MatchRepository matchRepository;
    private final PlayerTeamMembershipRepository playerTeamMembershipRepository;
    private final MatchEventRepository matchEventRepository;

    @Override
    public UUIDRepository<Team> getUUIDRepository() {
        return teamRepository;
    }

    @Transactional
    public void addPlayer(UUID playerUUID, UUID teamUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        player.setTeam(team);
    }

    @Transactional
    public void addStaff(UUID staffUUID, UUID teamUUID) {
        Staff staff = staffRepository.findByUUID(staffUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        staff.setTeam(team);
    }

    @Transactional
    public void removePlayer(UUID playerUUID, UUID teamUUID) {
        Team team = teamRepository.findByUUID(teamUUID);
        Player player = playerRepository.findByUUID(playerUUID);
        if(!player.getTeam().equals(team)){
            throw new IllegalArgumentException("Player is not in this team");
        }
        player.setTeam(null);
    }

    @Transactional
    public void removeStaff(UUID staffUUID, UUID teamUUID) {
        Team team = teamRepository.findByUUID(teamUUID);
        Staff staff = staffRepository.findByUUID(staffUUID);
        if(!staff.getTeam().equals(team)){
            throw new IllegalArgumentException("Staff is not in this team");
        }
        staff.setTeam(null);
    }

    public List<Player> getAllPlayersFromTeam(UUID teamUUID) {
        return List.copyOf(playerRepository.getAllPlayersByTeam(teamUUID));
    }

    public List<Staff> getAllStaffFromTeam(UUID teamUUID) {
        return List.copyOf(staffRepository.getAllStaffsByTeam(teamUUID));
    }

    public List<Match> getAllMatchesFromTeam(UUID teamUUID) {
        return List.copyOf(matchRepository.findAllByTeamUUID(teamUUID));
    }

    public List<PlayerTeamMembership> getAllPlayerTeamMembershipsFromTeam(UUID teamUUID) {
        return List.copyOf(playerTeamMembershipRepository.findAllByTeamUUID(teamUUID));
    }

    public List<MatchEvent> getAllEventsFromTeam(UUID teamUUID) {
        List<MatchEvent> events = new ArrayList<>();
        for (Match match : getAllMatchesFromTeam(teamUUID)) {
            events.addAll(matchEventRepository.getAllByMatchUuid(match.getUuid()));
        }
        return List.copyOf(events);
    }

    @Override
    public Team delete(UUID uuid) {
        Team team = teamRepository.findByUUID(uuid);
        team.setArchived(true);

        playerRepository.getAllPlayersByTeam(uuid).forEach(player -> {
            player.setTeam(null);
            playerRepository.save(player);
        });

        staffRepository.getAllStaffsByTeam(uuid).forEach(staff -> {
            staff.setTeam(null);
            staffRepository.save(staff);
        });

        return teamRepository.save(team);
    }

}
