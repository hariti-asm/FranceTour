package com.hariti.asmaa.FranceTour.dtos.mappers;

import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionSummaryDTO;
import com.hariti.asmaa.FranceTour.dtos.stage.StageResponseDTO;
import com.hariti.asmaa.FranceTour.entities.Competition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CompetitionMapper {
    private final StageMapper stageMapper;
    private Set<StageResponseDTO> stages = new HashSet<>();

    public CompetitionMapper(StageMapper stageMapper) {
        this.stageMapper = stageMapper;
    }
    public Competition toEntity(CompetitionRequestDTO dto) {
        Competition competition = new Competition();
        competition.setId(dto.getId());
        competition.setName(dto.getName());
        competition.setStartDate(dto.getStartDate());
        competition.setEndDate(dto.getEndDate());
        competition.setLocation(dto.getLocation());
        if (dto.getStages() != null) {
            competition.setStages(
                    (Set<com.hariti.asmaa.FranceTour.entities.Stage>) dto.getStages().stream()
                            .map((StageResponseDTO stageDto) ->
                                    stageMapper.toEntity(stageDto))
                            .collect(Collectors.toCollection(ArrayList::new))
            );
        }
        return competition;
    }

    public CompetitionRequestDTO toRequestDTO(Competition competition) {
        CompetitionRequestDTO dto = new CompetitionRequestDTO();
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());
        return dto;
    }



    public CompetitionResponseDTO toResponseDTO(Competition competition) {
        CompetitionResponseDTO dto = new CompetitionResponseDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());
        dto.setStages(new HashSet<StageResponseDTO>());  // Explicitly specify type

        if (competition.getStages() != null) {
            competition.getStages().forEach(stage ->
                    dto.getStages().add(stageMapper.toResponseDTO(stage))
            );
        }
        return dto;
    }

    public CompetitionSummaryDTO toSummaryDTO(Competition competition) {
        CompetitionSummaryDTO dto = new CompetitionSummaryDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());
        dto.setTotalStages(competition.getStages() != null ? competition.getStages().size() : 0);
        return dto;
    }
}