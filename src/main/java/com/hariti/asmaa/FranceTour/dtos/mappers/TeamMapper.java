
package com.hariti.asmaa.FranceTour.dtos.mappers;

import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.team.TeamRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.team.TeamResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.team.TeamSummaryDTO;
import com.hariti.asmaa.FranceTour.entities.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeamMapper {
    private final CyclistMapper cyclistMapper;

    public TeamResponseDTO toResponseDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamResponseDTO responseDTO = new TeamResponseDTO();
        responseDTO.setId(team.getId());
        responseDTO.setName(team.getName());

        if (team.getCyclists() != null) {
            responseDTO.setCyclists(
                    team.getCyclists().stream()
                            .map(cyclistMapper::toResponseDTO)
                            .collect(Collectors.toSet())
            );
        }

        return responseDTO;
    }

    public TeamSummaryDTO toSummaryDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamSummaryDTO summaryDTO = new TeamSummaryDTO();
        summaryDTO.setId(team.getId());
        summaryDTO.setName(team.getName());

        return summaryDTO;
    }

    public Team toEntity(TeamRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Team request DTO cannot be null");
        }

        validateRequestDTO(requestDTO);

        Team team = new Team();
        updateEntityFromDTO(team, requestDTO);
        return team;
    }

    public void updateEntityFromDTO(Team team, TeamRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Team request DTO cannot be null");
        }
        if (team == null) {
            throw new IllegalArgumentException("Team entity cannot be null");
        }

        validateRequestDTO(requestDTO);
        team.setName(requestDTO.getName());
    }

    private void validateRequestDTO(TeamRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
    }
}