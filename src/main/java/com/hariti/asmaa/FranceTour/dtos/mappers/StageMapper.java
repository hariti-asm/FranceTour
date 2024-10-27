package com.hariti.asmaa.FranceTour.dtos.mappers;

import com.hariti.asmaa.FranceTour.dtos.stage.StageRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.stage.StageResponseDTO;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import com.hariti.asmaa.FranceTour.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StageMapper {

    private final CompetitionMapper competitionMapper;
    private final CompetitionService competitionService;

    public StageResponseDTO toResponseDTO(Stage stage) {
        if (stage == null) {
            return null;
        }

        StageResponseDTO responseDTO = new StageResponseDTO();
        responseDTO.setId(stage.getId());
        responseDTO.setName(stage.getName());
        responseDTO.setStageNumber(stage.getStageNumber());
        responseDTO.setStartLocation(stage.getStartLocation());
        responseDTO.setEndLocation(stage.getEndLocation());
        responseDTO.setDistance(stage.getDistance());
        responseDTO.setDate(stage.getDate());

        if (stage.getCompetition() != null) {
            responseDTO.setCompetition(competitionMapper.toSummaryDTO(stage.getCompetition()));
        }

        return responseDTO;
    }

    public StageRequestDTO toRequestDTO(Stage stage) {
        if (stage == null) {
            return null;
        }

        StageRequestDTO requestDTO = new StageRequestDTO();
        requestDTO.setName(stage.getName());
        requestDTO.setStageNumber(stage.getStageNumber());
        requestDTO.setStartLocation(stage.getStartLocation());
        requestDTO.setEndLocation(stage.getEndLocation());
        requestDTO.setDistance(stage.getDistance());
        requestDTO.setDate(stage.getDate());

        if (stage.getCompetition() != null) {
            requestDTO.setCompetitionId(stage.getCompetition().getId());
        }

        return requestDTO;
    }

    public Stage toEntity(StageRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Stage request DTO cannot be null");
        }

        validateRequestDTO(requestDTO);

        Stage stage = new Stage();
        updateEntityFromDTO(stage, requestDTO);
        return stage;
    }

    public void updateEntityFromDTO(Stage stage, StageRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Stage request DTO cannot be null");
        }
        if (stage == null) {
            throw new IllegalArgumentException("Stage entity cannot be null");
        }

        validateRequestDTO(requestDTO);

        stage.setName(requestDTO.getName());
        stage.setStageNumber(requestDTO.getStageNumber());
        stage.setStartLocation(requestDTO.getStartLocation());
        stage.setEndLocation(requestDTO.getEndLocation());
        stage.setDistance(requestDTO.getDistance());
        stage.setDate(requestDTO.getDate());

        if (requestDTO.getCompetitionId() != null) {
            Competition competition = competitionService.findCompetitionById(requestDTO.getCompetitionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Competition not found with id: " + requestDTO.getCompetitionId()));
            stage.setCompetition(competition);
        }
    }

    private void validateRequestDTO(StageRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Stage name cannot be null or empty");
        }
        if (requestDTO.getStageNumber() == null || requestDTO.getStageNumber() <= 0) {
            throw new IllegalArgumentException("Stage number must be positive");
        }
        if (requestDTO.getStartLocation() == null || requestDTO.getStartLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Start location cannot be null or empty");
        }
        if (requestDTO.getEndLocation() == null || requestDTO.getEndLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("End location cannot be null or empty");
        }
        if (requestDTO.getDistance() <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }
        if (requestDTO.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (requestDTO.getCompetitionId() == null) {
            throw new IllegalArgumentException("Competition ID cannot be null");
        }
    }
}