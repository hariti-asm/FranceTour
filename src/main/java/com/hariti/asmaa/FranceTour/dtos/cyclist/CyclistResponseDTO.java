package com.hariti.asmaa.FranceTour.dtos.cyclist;

import com.hariti.asmaa.FranceTour.dtos.team.TeamSummaryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CyclistResponseDTO {
    private Long id;
    private String name;
    private String nationality;
    private Integer number;
    private TeamSummaryDTO team;
}