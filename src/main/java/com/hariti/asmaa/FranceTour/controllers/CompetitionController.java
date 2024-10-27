package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionSummaryDTO;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import com.hariti.asmaa.FranceTour.dtos.mappers.CompetitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;
    private final CompetitionMapper competitionMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CompetitionSummaryDTO>>> getAllCompetitions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompetitionSummaryDTO> competitions = competitionService.findAllCompetitions(pageable)
                .map(competitionMapper::toSummaryDTO);
        return ResponseBuilder.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompetitionResponseDTO>> getCompetitionById(@PathVariable Long id) {
        return competitionService.findCompetitionById(id)
                .map(competition -> ResponseBuilder.ok(competitionMapper.toResponseDTO(competition)))
                .orElse(ResponseBuilder.notFound("Competition not found"));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<CompetitionResponseDTO>> getCompetitionByName(@PathVariable String name) {
        Competition competition = competitionService.findCompetitionByName(name);
        if (competition != null) {
            return ResponseBuilder.ok(competitionMapper.toResponseDTO(competition));
        }
        return ResponseBuilder.notFound("Competition not found");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompetitionResponseDTO>> createCompetition(
            @RequestBody CompetitionRequestDTO requestDTO) {
        Competition savedCompetition = competitionService.saveCompetition(requestDTO);
        return ResponseBuilder.created(competitionMapper.toResponseDTO(savedCompetition));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompetitionResponseDTO>> updateCompetition(
            @PathVariable Long id,
            @RequestBody CompetitionRequestDTO requestDTO
    ) {
        try {
            Competition updatedCompetition = competitionService.updateCompetition(requestDTO);
            return ResponseBuilder.ok(competitionMapper.toResponseDTO(updatedCompetition));
        } catch (RuntimeException e) {
            return ResponseBuilder.notFound("Competition not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCompetition(@PathVariable Long id) {
        if (competitionService.findCompetitionById(id).isPresent()) {
            competitionService.deleteCompetition(id);
            return ResponseBuilder.ok("Competition deleted successfully");
        }
        return ResponseBuilder.notFound("Competition not found with id: " + id);
    }
}