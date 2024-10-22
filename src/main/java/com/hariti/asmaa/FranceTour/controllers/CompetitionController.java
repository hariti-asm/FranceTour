package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<Competition>>>> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Competition> competitions = competitionService.findAllCompetitions(pageable);
        return ResponseBuilder.ok(Optional.of(competitions));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Competition>> getCompetitionById(@PathVariable Long id) {
        return competitionService.findCompetitionById(id)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("Competition not found"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Competition>> getCompetitionByName(@PathVariable String name) {
        Competition competition = competitionService.findCompetitionByName(name);
        return competition != null
                ? ResponseBuilder.ok(competition)
                : ResponseBuilder.notFound("Competition not found");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Competition>> createCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.saveCompetition(competition);
        return ResponseBuilder.created(savedCompetition);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Competition>> updateCompetition(
            @PathVariable Long id,
            @RequestBody Competition competition
    ) {
        try {
            competition.setId(id);
            Competition updatedCompetition = competitionService.updateCompetition(competition);
            return ResponseBuilder.created(updatedCompetition);
        } catch (RuntimeException e) {
            return ResponseBuilder.notFound("Competition not found with id: " + id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCompetition(@PathVariable Long id) {
        if (competitionService.findCompetitionById(id).isPresent()) {
            competitionService.deleteCompetition(id);
            return ResponseBuilder.ok(null);
        }
        return ResponseBuilder.notFound("Competition not found with id: " + id);
    }

}
