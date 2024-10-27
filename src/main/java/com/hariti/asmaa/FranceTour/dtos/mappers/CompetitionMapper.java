package com.hariti.asmaa.FranceTour.dtos.mappers;

import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionSummaryDTO;
import com.hariti.asmaa.FranceTour.dtos.stage.StageResponseDTO;
import com.hariti.asmaa.FranceTour.entities.Competition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CompetitionMapper {
    private final StageMapper stageMapper;

    public Competition toEntity(CompetitionRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Competition request DTO cannot be null");
        }

        Competition competition = new Competition();
        competition.setName(dto.getName());
        competition.setStartDate(dto.getStartDate());
        competition.setEndDate(dto.getEndDate());
        competition.setLocation(dto.getLocation());
        competition.setStages(new HashSet<>());  // Initialize empty set of stages

        return competition;
    }

    public CompetitionRequestDTO toRequestDTO(Competition competition) {
        if (competition == null) {
            return null;
        }

        CompetitionRequestDTO dto = new CompetitionRequestDTO();
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());
        return dto;
    }

    public CompetitionResponseDTO toResponseDTO(Competition competition) {
        if (competition == null) {
            return null;
        }

        CompetitionResponseDTO dto = new CompetitionResponseDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());

        // Map stages if they exist
        Set<StageResponseDTO> stageDTOs = new HashSet<>();
        if (competition.getStages() != null) {
            competition.getStages().forEach(stage ->
                    stageDTOs.add(stageMapper.toResponseDTO(stage))
            );
        }
        dto.setStages(stageDTOs);

        return dto;
    }

    public CompetitionSummaryDTO toSummaryDTO(Competition competition) {
        if (competition == null) {
            return null;
        }

        CompetitionSummaryDTO dto = new CompetitionSummaryDTO();
        dto.setId(competition.getId());
        dto.setName(competition.getName());
        dto.setStartDate(competition.getStartDate());
        dto.setEndDate(competition.getEndDate());
        dto.setLocation(competition.getLocation());
        dto.setTotalStages(competition.getStages() != null ? competition.getStages().size() : 0);
        return dto;
    }

    private void validateCompetitionDTO(CompetitionRequestDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Competition name cannot be null or empty");
        }
        if (dto.getStartDate() == null) {
            throw new IllegalArgumentException("Start date cannot be null");
        }
        if (dto.getEndDate() == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }
        if (dto.getLocation() == null || dto.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (dto.getEndDate().isBefore(dto.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
    }
}