package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.dtos.stage.StageRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.stage.StageResponseDTO;
import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import com.hariti.asmaa.FranceTour.services.StageService;
import com.hariti.asmaa.FranceTour.dtos.mappers.StageMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stages")
@RequiredArgsConstructor
public class StageController {

    private final StageService stageService;
    private final StageMapper stageMapper;

    // GET /api/v1/stages with pagination
    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<Stage>>>> getAllStages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Stage> stages = stageService.findAllStages(pageable)
                .map(stage -> stageMapper.toEntity(stageMapper.toRequestDTO(stage)));
        return ResponseBuilder.ok(Optional.of(stages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StageResponseDTO>> getStageById(@PathVariable Long id) {
        return stageService.findStageById(id)
                .map(stage -> ResponseBuilder.ok(stageMapper.toResponseDTO(stage)))
                .orElse(ResponseBuilder.notFound("Stage not found"));
    }

    @PostMapping("/stages")
    public ResponseEntity<StageResponseDTO> createStage(@Valid @RequestBody StageRequestDTO requestDTO) {
        Stage stage = stageMapper.toEntity(requestDTO);
        stageService.saveStage(stage);
        return ResponseEntity.status(HttpStatus.CREATED).body(stageMapper.toResponseDTO(stage));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Stage>> updateStage(
            @PathVariable Long id,
            @RequestBody Stage stage) {
        try {
            stage.setId(id);
            StageRequestDTO requestDTO = stageMapper.toRequestDTO(stage);
            Stage updatedStage = stageService.updateStage(stageMapper.toEntity(requestDTO));
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