package com.hariti.asmaa.FranceTour.controllers;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Stage;
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

    @GetMapping
    public ResponseEntity<ApiResponse<Optional<Page<Cyclist>>>> getAllCyclists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cyclist> cyclists = cyclistService.findAllCyclists(pageable);
        return ResponseBuilder.ok(Optional.of(cyclists));
    }
  

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cyclist>> getCyclistById(@PathVariable Long id) {
        return cyclistService.findCyclistById(id)
                .map(ResponseBuilder::ok)
                .orElse(ResponseBuilder.notFound("Cyclist not found with id: " + id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Cyclist>> getCyclistByName(@PathVariable String name) {
        Cyclist cyclist = cyclistService.findCyclistByName(name);
        return cyclist != null
                ? ResponseBuilder.ok(cyclist)
                : ResponseBuilder.notFound("Cyclist not found with name: " + name);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cyclist>> createCyclist(@RequestBody Cyclist cyclist) {
        Cyclist savedCyclist = cyclistService.save(cyclist);
        return ResponseBuilder.created(savedCyclist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cyclist>> updateCyclist(
            @PathVariable Long id,
            @RequestBody Cyclist cyclist
    ) {
        return cyclistService.findCyclistById(id)
                .map(existingCyclist -> {
                    cyclist.setId(id);
                    Cyclist updatedCyclist = cyclistService.updateCyclist(cyclist);
                    return ResponseBuilder.ok(updatedCyclist);
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
