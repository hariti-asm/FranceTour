package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Team;
import com.hariti.asmaa.FranceTour.services.TeamService;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Team>>> getAllTeams() {
        return ResponseBuilder.ok(teamService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Team>> getTeamById(@PathVariable Long id) {
        return teamService.findById(id)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("Team not found with id: " + id));
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Team>> getTeamByName(@PathVariable String name) {
        Team team = teamService.findByName(name);
        return team != null
                ? ResponseBuilder.ok(team)
                : ResponseBuilder.notFound("Team not found with name: " + name);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Team>> createTeam(@RequestBody Team team) {
        return ResponseBuilder.created(teamService.save(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Team>> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.findById(id)
                .map(existingTeam -> {
                    team.setId(id);
                    return ResponseBuilder.ok(teamService.update(team));
                })
                .orElse(ResponseBuilder.notFound("Team not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long id) {
        if (teamService.findById(id).isPresent()) {
            teamService.deleteById(id);
            return ResponseBuilder.ok(null);
        }
        return ResponseBuilder.notFound("Team not found with id: " + id);
    }
}