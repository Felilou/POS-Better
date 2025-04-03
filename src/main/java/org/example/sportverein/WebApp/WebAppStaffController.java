package org.example.sportverein.WebApp;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.persons.staff.CreateStaffDTO;
import org.example.sportverein.persons.staff.Staff;
import org.example.sportverein.persons.staff.StaffService;
import org.example.sportverein.persons.staff.UpdateStaffDTO;
import org.example.sportverein.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class WebAppStaffController {

    private final StaffService staffService;
    private final TeamService teamService;

    @GetMapping
    protected final String overview(Model model) {
        model.addAttribute("staffs", staffService.getAll());
        model.addAttribute("title", "Staff Overview");
        return "staff/overview";
    }

    @GetMapping("/{uuid}")
    protected final String detail(Model model, @PathVariable UUID uuid) {
        Staff staff = staffService.getByUUID(uuid);
        model.addAttribute("staff", staff);
        model.addAttribute("title", "Staff "+staff.getFirstName()+" "+staff.getLastName());
        return "staff/detail";
    }

    @GetMapping("/{uuid}/edit")
    protected final String edit(Model model, @PathVariable UUID uuid) {
        Staff staff = staffService.getByUUID(uuid);
        model.addAttribute("staff", staff);
        model.addAttribute("teams", teamService.getAll());
        model.addAttribute("title", "Edit staff "+staff.getFirstName()+" "+staff.getLastName());
        return "staff/edit";
    }

    @GetMapping("/add")
    protected final String add(Model model) {
        model.addAttribute("title", "Add staff");
        model.addAttribute("teams", teamService.getAll());
        return "staff/add";
    }

    @PostMapping("/add")
    protected final String postAdd(@ModelAttribute CreateStaffDTO dto, Model model) {
        UUID created = staffService.create(dto).getUuid();
        return "redirect:/staff/" + created;
    }

    @PostMapping("/{uuid}/edit")
    protected final String postEdit(@ModelAttribute UpdateStaffDTO dto, @PathVariable UUID uuid, Model model) {
        UUID updated = staffService.updateEntity(dto, uuid).getUuid();
        return "redirect:/staff/" + updated;
    }

    @PostMapping("/{uuid}/team")
    public String addStaffToTeam(@RequestParam("teamUUID") UUID teamUUID, @PathVariable UUID uuid, Model model) {
        staffService.addStaffToTeam(uuid, teamUUID);
        return "redirect:/staff/" + uuid;
    }

    @PostMapping("/{uuid}/remove")
    public String removeStaffFromTeam(@PathVariable UUID uuid, Model model) {
        staffService.delete(uuid);
        return "redirect:/staff";
    }

}
