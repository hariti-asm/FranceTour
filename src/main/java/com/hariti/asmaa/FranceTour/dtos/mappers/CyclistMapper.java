package com.hariti.asmaa.FranceTour.dtos.mappers;


import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.cyclist.CyclistResponseDTO;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Team;
import com.hariti.asmaa.FranceTour.exceptions.ResourceNotFoundException;
import com.hariti.asmaa.FranceTour.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CyclistMapper {
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    public CyclistResponseDTO toResponseDTO(Cyclist cyclist) {
        if (cyclist == null) {
            return null;
        }

        CyclistResponseDTO responseDTO = new CyclistResponseDTO();
        responseDTO.setId(cyclist.getId());
        responseDTO.setName(cyclist.getFirstName());
        responseDTO.setNationality(cyclist.getNationality());

        if (cyclist.getTeam() != null) {
            responseDTO.setTeam(teamMapper.toSummaryDTO(cyclist.getTeam()));
        }

        return responseDTO;
    }

    public Cyclist toEntity(CyclistRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Cyclist request DTO cannot be null");
        }

        validateRequestDTO(requestDTO);

        Cyclist cyclist = new Cyclist();
        updateEntityFromDTO(cyclist, requestDTO);
        return cyclist;
    }

    public void updateEntityFromDTO(Cyclist cyclist, CyclistRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Cyclist request DTO cannot be null");
        }
        if (cyclist == null) {
            throw new IllegalArgumentException("Cyclist entity cannot be null");
        }

        validateRequestDTO(requestDTO);

        cyclist.setName(requestDTO.getName());
        cyclist.setNationality(requestDTO.getNationality());
        cyclist.setNumber(requestDTO.getNumber());

        if (requestDTO.getTeamId() != null) {
            Team team = teamService.findTeamById(requestDTO.getTeamId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Team not found with id: " + requestDTO.getTeamId()));
            cyclist.setTeam(team);
        }
    }

    private void validateRequestDTO(CyclistRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Cyclist name cannot be null or empty");
        }
        if (requestDTO.getNationality() == null || requestDTO.getNationality().trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be null or empty");
        }
        if (requestDTO.getNumber() == null || requestDTO.getNumber() <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        if (requestDTO.getTeamId() == null) {
            throw new IllegalArgumentException("Team ID cannot be null");
        }
    }
}
