package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import com.hariti.asmaa.FranceTour.services.StageResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stage-results")
@RequiredArgsConstructor
public class StageResultController {
    private final StageResultService stageResultService;

    // GET /api/v1/stage-results (with pagination)
    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<StageResult>>>> getAllStageResults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StageResult> stageResults = stageResultService.findAllStageResults(pageable);
        return ResponseBuilder.ok(Optional.of(stageResults));
    }

    // GET /api/v1/stage-results/{stageId}/{cyclistId}
    @GetMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<ApiResponse<StageResult>> getStageResultByIds(
            @PathVariable Long stageId,
            @PathVariable Long cyclistId) {
        return stageResultService.findByStageAndCyclist(stageId, cyclistId)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("Stage result not found"));
    }

    // POST /api/v1/stage-results
    @PostMapping
    public ResponseEntity<ApiResponse<StageResult>> createStageResult(
            @RequestBody StageResult stageResult) {
        StageResult savedStageResult = stageResultService.createStageResult(stageResult.getCyclist().getId(), stageResult.getStage().getId(), stageResult.getPosition(), stageResult.getTime());
        return ResponseBuilder.created(savedStageResult);
    }

    // DELETE /api/v1/stage-results/{stageId}/{cyclistId}
    @DeleteMapping("/{stageId}/{cyclistId}")
    public ResponseEntity<ApiResponse<String>> deleteStageResult(
            @PathVariable Long stageId,
            @PathVariable Long cyclistId) {
        try {
            stageResultService.deleteStageResult(cyclistId, stageId);
            return ResponseBuilder.ok("Stage result deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound("Stage result not found with provided ids.");
        }
    }
}
