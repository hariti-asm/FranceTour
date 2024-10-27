package com.hariti.asmaa.FranceTour.dtos.team;


import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistResponseDTO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TeamResponseDTO {
    private Long id;
    private String name;
    private Set<CyclistResponseDTO> cyclists = new HashSet<>();
}