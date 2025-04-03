package org.example.sportverein.WebApp;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.persons.player.PlayerService;
import org.example.sportverein.persons.staff.StaffService;
import org.example.sportverein.team.CreateTeamDTO;
import org.example.sportverein.team.Team;
import org.example.sportverein.team.TeamService;
import org.example.sportverein.team.UpdateTeamDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/teams")
@RequiredArgsConstructor
public class WebAppTeamController {
    
    private final TeamService teamService;
    private final PlayerService playerService;
    private final StaffService staffService;

    @GetMapping
    protected final String overview(Model model) {
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("title", "Team Overview");
        return "team/overview";
    }

    @GetMapping("/{uuid}")
    protected final String detail(Model model, @PathVariable UUID uuid) {
        Team team = teamService.getByUUID(uuid);
        model.addAttribute("team", team);
        model.addAttribute("matchEvents", teamService.getAllEventsFromTeam(uuid));
        model.addAttribute("events", teamService.getAllEventsFromTeam(uuid));
        model.addAttribute("AllPlayers", playerService.getAll());
        model.addAttribute("AllStaff", staffService.getAll());
        model.addAttribute("players", teamService.getAllPlayersFromTeam(uuid));
        model.addAttribute("staff", teamService.getAllStaffFromTeam(uuid));
        model.addAttribute("playerHistory", teamService.getAllPlayersFromTeam(uuid));
        model.addAttribute("title", "Team "+team.getName());
        return "team/detail";
    }

    @GetMapping("/{uuid}/edit")
    protected final String edit(Model model, @PathVariable UUID uuid) {
        Team team = teamService.getByUUID(uuid);
        model.addAttribute("team", team);
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("title", "Edit team "+team.getName());
        return "team/edit";
    }

    @GetMapping("/add")
    protected final String add(Model model) {
        model.addAttribute("title", "Add team");
        return "team/add";
    }

    @PostMapping("/add")
    protected final String postAdd(@ModelAttribute CreateTeamDTO dto, Model model) {
        UUID created = teamService.create(dto).getUuid();
        return "redirect:/teams/" + created;
    }

    @PostMapping("/{uuid}/edit")
    protected final String postEdit(@ModelAttribute UpdateTeamDTO dto, @PathVariable UUID uuid, Model model) {
        UUID updated = teamService.updateEntity(dto, uuid).getUuid();
        return "redirect:/teams/" + updated;
    }

    @PostMapping("/{uuid}/player")
    public String addPlayerToTeam(@RequestParam("playerUUID") UUID teamUUID, @PathVariable UUID uuid, Model model) {
        teamService.addPlayer(uuid, teamUUID);
        return "redirect:/teams/" + uuid;
    }

    @PostMapping("/{uuid}/staff")
    public String addStaffToTeam(@RequestParam("staffUUID") UUID staffUUID, @PathVariable UUID uuid, Model model) {
        teamService.addStaff(staffUUID, uuid);
        return "redirect:/teams/" + uuid;
    }

    @PostMapping("/{uuid}/staff/{staffUUID}/remove")
    public String removeStaffFromTeam(@PathVariable UUID staffUUID, @PathVariable UUID uuid, Model model) {
        teamService.removeStaff(staffUUID, uuid);
        return "redirect:/teams/" + uuid;
    }

    @PostMapping("/{uuid}/player/{playerUUID}/remove")
    public String removePlayerFromTeam(@PathVariable UUID playerUUID, @PathVariable UUID uuid, Model model) {
        teamService.removePlayer(playerUUID, uuid);
        return "redirect:/teams/" + uuid;
    }


}
