package org.example.sportverein.WebApp;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.match.MatchEvent;
import org.example.sportverein.match.MatchService;
import org.example.sportverein.persons.player.*;
import org.example.sportverein.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class WebAppPlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    protected final String overview(Model model) {
        model.addAttribute("players", playerService.getAll());
        model.addAttribute("title", "Player Overview");
        return "player/overview";
    }

    @GetMapping("/{uuid}")
    protected final String detail(Model model, @PathVariable UUID uuid) {
        Player player = playerService.getByUUID(uuid);
        model.addAttribute("player", player);
        model.addAttribute("matchEvents", playerService.getMatchEventsOfPlayer(uuid));
        model.addAttribute("title", "Player "+player.getFirstName()+" "+player.getLastName());
        return "player/detail";
    }

    @GetMapping("/{uuid}/edit")
    protected final String edit(Model model, @PathVariable UUID uuid) {
        Player player = playerService.getByUUID(uuid);
        model.addAttribute("player", player);
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("title", "Edit player "+player.getFirstName()+" "+player.getLastName());
        return "player/edit";
    }

    @GetMapping("/add")
    protected final String add(Model model) {
        model.addAttribute("title", "Add player");
        model.addAttribute("teams", teamService.getAll());
        return "player/add";
    }

    @PostMapping("/add")
    protected final String postAdd(@ModelAttribute CreatePlayerDTO dto, Model model) {
        UUID created = playerService.create(dto).getUuid();
        return "redirect:/players/" + created;
    }

    @PostMapping("/{uuid}/edit")
    protected final String postEdit(@ModelAttribute UpdatePlayerDTO dto, @PathVariable UUID uuid, Model model) {
        UUID updated = playerService.updateEntity(dto, uuid).getUuid();
        return "redirect:/players/" + updated;
    }

    @PostMapping("/{uuid}/team")
    public String addPlayerToTeam(@RequestParam("teamUUID") UUID teamUUID, @PathVariable UUID uuid, Model model) {
        playerService.addPlayerToTeam(uuid, teamUUID);
        return "redirect:/players/" + uuid;
    }

}
