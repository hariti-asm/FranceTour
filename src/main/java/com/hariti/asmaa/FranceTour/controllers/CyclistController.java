package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.mappers.CyclistMapper;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.response.ApiResponse;
import com.hariti.asmaa.FranceTour.response.ResponseBuilder;
import com.hariti.asmaa.FranceTour.services.CyclistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cyclists")
@RequiredArgsConstructor
public class CyclistController {
    private final CyclistService cyclistService;
    private final CyclistMapper cyclistMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<CyclistResponseDTO>>>> getAllCyclists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cyclist> cyclists = cyclistService.findAllCyclists(pageable);
        Page<CyclistResponseDTO> cyclistDTOs = cyclists.map(cyclistMapper::toResponseDTO);
        return ResponseBuilder.ok(Optional.of(cyclistDTOs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CyclistResponseDTO>> getCyclistById(@PathVariable Long id) {
        return cyclistService.findCyclistById(id)
                .map(cyclist -> ResponseBuilder.ok(cyclistMapper.toResponseDTO(cyclist)))
                .orElse(ResponseBuilder.notFound("Cyclist not found with id: " + id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<CyclistResponseDTO>> getCyclistByName(@PathVariable String name) {
        Cyclist cyclist = cyclistService.findCyclistByName(name);
        return cyclist != null
                ? ResponseBuilder.ok(cyclistMapper.toResponseDTO(cyclist))
                : ResponseBuilder.notFound("Cyclist not found with name: " + name);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CyclistResponseDTO>> createCyclist(@RequestBody CyclistRequestDTO cyclistDTO) {
        Cyclist cyclist = cyclistMapper.toEntity(cyclistDTO);
        Cyclist savedCyclist = cyclistService.save(cyclist);
        return ResponseBuilder.created(cyclistMapper.toResponseDTO(savedCyclist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CyclistResponseDTO>> updateCyclist(
            @PathVariable Long id,
            @RequestBody CyclistRequestDTO cyclistDTO
    ) {
        return cyclistService.findCyclistById(id)
                .map(existingCyclist -> {
                    cyclistMapper.updateEntityFromDTO(existingCyclist, cyclistDTO);
                    Cyclist updatedCyclist = cyclistService.updateCyclist(existingCyclist);
                    return ResponseBuilder.ok(cyclistMapper.toResponseDTO(updatedCyclist));
                })
                .orElse(ResponseBuilder.notFound("Cyclist not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCyclist(@PathVariable Long id) {
        if (cyclistService.findCyclistById(id).isPresent()) {
            cyclistService.deleteCyclist(id);
            return ResponseBuilder.ok(null);
        }
        return ResponseBuilder.notFound("Cyclist not found with id: " + id);
    }
}