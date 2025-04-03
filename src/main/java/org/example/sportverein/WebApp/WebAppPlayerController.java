package org.example.sportverein.WebApp;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.match.MatchEvent;
import org.example.sportverein.persons.player.*;
import org.example.sportverein.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/webapp/players")
@RequiredArgsConstructor
public class WebAppPlayerController {

    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping
    public String overview(Model model) {
        List<Player> players = playerService.getAll();
        model.addAttribute("players", players);
        model.addAttribute("title", "Players Overview");
        return "players/overview";
    }

    @GetMapping("/{uuid}")
    public String detail(@PathVariable UUID uuid, Model model) {
        Player player = playerService.getByUUID(uuid);
        List<MatchEvent> matchEvents = playerService.getMatchEventsOfPlayer(uuid);
        PlayerDTO playerDTO = PlayerDTO.fromPlayer(player, matchEvents);

        model.addAttribute("player", playerDTO);
        model.addAttribute("title", player.getFirstName() + " " + player.getLastName());
        return "players/detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("playerDTO", new CreatePlayerDTO(null, null, null, null, null));
        model.addAttribute("title", "Add New Player");
        return "players/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("playerDTO") CreatePlayerDTO playerDTO,
                      BindingResult bindingResult,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Add New Player");
            return "players/add";
        }

        Player player = playerService.create(playerDTO);
        return "redirect:/webapp/players/" + player.getUuid();
    }

    @GetMapping("/{uuid}/edit")
    public String editForm(@PathVariable UUID uuid, Model model) {
        Player player = playerService.getByUUID(uuid);

        UpdatePlayerDTO updatePlayerDTO = new UpdatePlayerDTO(
            player.getFirstName(),
            player.getLastName(),
            player.getBirthDate(),
            player.getPhoneNumber().toString(),
            player.getEmail()
        );

        model.addAttribute("uuid", uuid);
        model.addAttribute("playerDTO", updatePlayerDTO);
        model.addAttribute("title", "Edit " + player.getFirstName() + " " + player.getLastName());
        return "players/edit";
    }

    @PostMapping("/{uuid}/edit")
    public String edit(@PathVariable UUID uuid,
                       @Valid @ModelAttribute("playerDTO") UpdatePlayerDTO playerDTO,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("uuid", uuid);
            model.addAttribute("title", "Edit Player");
            return "players/edit";
        }

        playerService.updateEntity(playerDTO, uuid);
        return "redirect:/webapp/players/" + uuid;
    }

    @PostMapping("/{uuid}/archive")
    public String archive(@PathVariable UUID uuid) {
        playerService.delete(uuid);
        return "redirect:/webapp/players";
    }

    @PostMapping("/{uuid}/team/remove")
    public String removeFromTeam(@PathVariable UUID uuid) {
        playerService.removePlayerFromTeam(uuid);
        return "redirect:/webapp/players/" + uuid;
    }

    @GetMapping("/{uuid}/team/add")
    public String addToTeamForm(@PathVariable UUID uuid, Model model) {
        Player player = playerService.getByUUID(uuid);
        model.addAttribute("player", player);
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("title", "Add " + player.getFirstName() + " to Team");
        return "players/add-to-team";
    }

    @PostMapping("/{playerUuid}/team/{teamUuid}")
    public String addToTeam(@PathVariable UUID playerUuid, @PathVariable UUID teamUuid) {
        playerService.addPlayerToTeam(playerUuid, teamUuid);
        return "redirect:/webapp/players/" + playerUuid;
    }
}
