package org.example.sportverein.WebApp;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.match.*;
import org.example.sportverein.persons.player.PlayerService;
import org.example.sportverein.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class WebAppMatchController {

    private final MatchService matchService;
    private final TeamService teamService;
    private final MatchEventService matchEventService;
    private final PlayerService playerService;

    @GetMapping
    protected final String overview(Model model) {
        model.addAttribute("matches", matchService.getAll());
        model.addAttribute("title", "Match Overview");
        return "match/overview";
    }

    @GetMapping("/{uuid}")
    protected final String detail(Model model, @PathVariable UUID uuid) {
        Match match = matchService.getByUUID(uuid);
        model.addAttribute("match", match);
        model.addAttribute("matchEvents", match.getEvents());
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("players", playerService.getAll());
        model.addAttribute("title", "Match "+match.getHomeTeam()+" Vs. "+match.getAwayTeam().getName());
        return "match/detail";
    }

    @GetMapping("/add")
    protected final String add(Model model) {
        model.addAttribute("title", "Add match");
        model.addAttribute("teams", teamService.getAll());
        return "match/add";
    }

    @PostMapping("/add")
    protected final String postAdd(@ModelAttribute CreateMatchDTO dto, Model model) {
        UUID created = matchService.create(dto).getUuid();
        return "redirect:/matches/" + created;
    }

    @PostMapping("/{uuid}/events")
    public String addMatchEventToMatch(@PathVariable UUID uuid, Model model, @ModelAttribute CreateMatchEventDTO dto) {
        matchService.addMatchEvent(uuid, dto);
        return "redirect:/matches/" + uuid;
    }

    @PostMapping("/{uuid}/events/{eventUUID}/edit")
    public String editMatchEvent(Model model, @ModelAttribute UpdateMatchEventDTO dto, @PathVariable UUID eventUUID, @PathVariable UUID uuid) {
        matchEventService.updateEntity(dto, eventUUID);
        return "redirect:/matches/" + uuid;
    }

    @PostMapping("/{uuid}/events/{eventUUID}/player")
    public String editMatchEvent(Model model, @RequestParam("playerUUID") UUID playerUUID, @PathVariable UUID eventUUID, @PathVariable UUID uuid) {
        matchEventService.setPlayerOfEvent(playerUUID, eventUUID);
        return "redirect:/matches/" + uuid;
    }

    @PostMapping("/{uuid}/events/{eventUUID}/remove")
    public String editMatchEvent(Model model, @PathVariable UUID eventUUID, @PathVariable UUID uuid) {
        matchService.removeMatchEvent(eventUUID);
        return "redirect:/matches/" + uuid;
    }

    
}
