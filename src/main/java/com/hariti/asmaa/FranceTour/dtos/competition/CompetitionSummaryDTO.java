package com.hariti.asmaa.FranceTour.dtos.competition;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompetitionSummaryDTO {
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private int totalStages;

    @Data
    public static class StageResponseDTO {
        private Long id;
        private String name;
        private Integer stageNumber;
        private String startLocation;
        private String endLocation;
        private double distance;
        private LocalDate date;
        private CompetitionSummaryDTO competition;
    }
}