package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import com.hariti.asmaa.FranceTour.services.GeneralResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/general-results")
@RequiredArgsConstructor
public class GeneralResultController {
    private final GeneralResultService generalResultService;

    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<GeneralResult>>>> getAllGeneralResults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<GeneralResult> generalResults = generalResultService.findAllGeneralResults(pageable);
        return ResponseBuilder.ok(Optional.of(generalResults));
    }


    @GetMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<ApiResponse<GeneralResult>> getGeneralResultByIds(
            @PathVariable Long competitionId,
            @PathVariable Long cyclistId) {
        return generalResultService.findByCyclistAndCompetition(competitionId, cyclistId)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("General result not found"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GeneralResult>> createGeneralResult(
            @RequestBody GeneralResult generalResult) {
        GeneralResult savedGeneralResult = generalResultService.createGeneralResult(generalResult);
        return ResponseBuilder.created(savedGeneralResult);
    }

    @DeleteMapping("/{competitionId}/{cyclistId}")
    public ResponseEntity<ApiResponse<String>> deleteGeneralResult(
            @PathVariable Long competitionId,
            @PathVariable Long cyclistId) {
        try {
            generalResultService.deleteGeneralResult(cyclistId, competitionId);
            return ResponseBuilder.ok("General result deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound("General result not found with provided ids.");
        }
    }

}
