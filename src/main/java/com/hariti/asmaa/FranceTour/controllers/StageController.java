package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.services.StageService;
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
@RequestMapping("/api/v1/stages")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;

    // GET /api/v1/stages with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<Stage>>>> getAllStages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Stage> stages = stageService.findAllStages(pageable);
        return ResponseBuilder.ok(Optional.of(stages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Stage>> getStageById(@PathVariable Long id) {
        return stageService.findStageById(id)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("Stage not found"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Stage>> createStage(@RequestBody Stage stage) {
        Stage savedStage = stageService.saveStage(stage);
        return ResponseBuilder.created(savedStage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Stage>> updateStage(
            @PathVariable Long id,
            @RequestBody Stage stage) {
        try {
            stage.setId(id);
            Stage updatedStage = stageService.updateStage(stage);
            return ResponseBuilder.created(updatedStage);
        } catch (RuntimeException e) {
            return ResponseBuilder.notFound("Stage not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStage(@PathVariable Long id) {
        if (stageService.findStageById(id).isPresent()) {
            stageService.deleteStage(id);
            return ResponseBuilder.ok(null);
        }
        return ResponseBuilder.notFound("Stage not found with id: " + id);
    }
}
