package com.hariti.asmaa.FranceTour.dtos.mappers;


import com.hariti.asmaa.FranceTour.dtos.stage.StageRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.stage.StageResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionSummaryDTO;
import com.hariti.asmaa.FranceTour.entities.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageMapper {
    public Stage toEntity(StageResponseDTO dto) {
        Stage stage = new Stage();
        stage.setId(dto.getId());
        stage.setName(dto.getName());
        stage.setStageNumber(dto.getStageNumber());
        stage.setStartLocation(dto.getStartLocation());
        stage.setEndLocation(dto.getEndLocation());
        stage.setDistance(dto.getDistance());
        stage.setDate(dto.getDate());
        return stage;
    }


    public StageResponseDTO toResponseDTO(Stage stage) {
        StageResponseDTO dto = new StageResponseDTO();
        dto.setId(stage.getId());
        dto.setName(stage.getName());
        dto.setStageNumber(stage.getStageNumber());
        dto.setStartLocation(stage.getStartLocation());
        dto.setEndLocation(stage.getEndLocation());
        dto.setDistance(stage.getDistance());
        dto.setDate(stage.getDate());

        if (stage.getCompetition() != null) {
            CompetitionSummaryDTO competitionDTO = new CompetitionSummaryDTO();
            competitionDTO.setId(stage.getCompetition().getId());
            competitionDTO.setName(stage.getCompetition().getName());
            competitionDTO.setStartDate(stage.getCompetition().getStartDate());
            competitionDTO.setEndDate(stage.getCompetition().getEndDate());
            competitionDTO.setLocation(stage.getCompetition().getLocation());
            competitionDTO.setTotalStages(stage.getCompetition().getStages().size());
            dto.setCompetition(competitionDTO);
        }

        return dto;
    }


}