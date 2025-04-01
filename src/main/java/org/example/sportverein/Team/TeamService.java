package org.example.sportverein.Team;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.Match.Match;
import org.example.sportverein.Match.MatchEvent;
import org.example.sportverein.Match.MatchEventRepository;
import org.example.sportverein.Match.MatchRepository;
import org.example.sportverein.UUIDRepository;
import org.example.sportverein.persons.Player.Player;
import org.example.sportverein.persons.Player.PlayerRepository;
import org.example.sportverein.persons.Player.PlayerTeamMembership;
import org.example.sportverein.persons.Player.PlayerTeamMembershipRepository;
import org.example.sportverein.persons.Staff.Staff;
import org.example.sportverein.persons.Staff.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
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

    public void addPlayer(UUID playerUUID, UUID teamUUID) {
        Player player = playerRepository.findByUUID(playerUUID);
        Team team = teamRepository.findByUUID(teamUUID);
        player.setTeam(team);
        playerRepository.save(player);
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

}
