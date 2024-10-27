package com.hariti.asmaa.FranceTour.dtos.stage;

import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionSummaryDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@Data
public class StageResponseDTO {
    private Long id;
    private String name;
    private Integer stageNumber;
    private String startLocation;
    private String endLocation;
    private double distance;
    private LocalDate date;
    private CompetitionSummaryDTO competition;
}