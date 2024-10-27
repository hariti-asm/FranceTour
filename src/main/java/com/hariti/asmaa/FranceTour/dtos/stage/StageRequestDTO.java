package com.hariti.asmaa.FranceTour.dtos.stage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StageRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Stage number is required")
    @Positive(message = "Stage number must be positive")
    private Integer stageNumber;

    @NotBlank(message = "Start location is required")
    private String startLocation;

    @NotBlank(message = "End location is required")
    private String endLocation;

    @Positive(message = "Distance must be positive")
    private double distance;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Competition ID is required")
    private Long competitionId;
}