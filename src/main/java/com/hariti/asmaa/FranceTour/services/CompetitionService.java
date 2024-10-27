package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionRequestDTO;
import com.hariti.asmaa.FranceTour.dtos.competition.CompetitionResponseDTO;
import com.hariti.asmaa.FranceTour.dtos.mappers.CompetitionMapper;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    public Competition findCompetitionByName(String name) {
        return competitionRepository.findCompetitionByName(name.toLowerCase());
    }

    public Optional<Competition> findCompetitionById(long id) {
        return competitionRepository.findById(id);
    }

    public Page<Competition> findAllCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    public Competition saveCompetition(CompetitionRequestDTO competitionDTO) {
        if (competitionDTO == null) {
            throw new IllegalArgumentException("Competition can't be created");
        }
        if (competitionDTO.getStartDate().isAfter(competitionDTO.getEndDate())) {
            throw new IllegalArgumentException("Competition start date can't be after end date");
        }

        Competition existingCompetition = competitionRepository.findCompetitionByName(competitionDTO.getName());
        if (existingCompetition != null) {
            throw new IllegalArgumentException("Competition already exists");
        }

        Competition competition = competitionMapper.toEntity(competitionDTO);
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(CompetitionRequestDTO competitionDTO) {
        if (competitionDTO.getStartDate().isAfter(competitionDTO.getEndDate())) {
            throw new IllegalArgumentException("Competition start date can't be after end date");
        }

        return competitionRepository.findById(competitionDTO .getId())
                .map(existingCompetition -> {
                    existingCompetition.setName(competitionDTO.getName());
                    existingCompetition.setStartDate(competitionDTO.getStartDate());
                    existingCompetition.setEndDate(competitionDTO.getEndDate());
                    existingCompetition.setLocation(competitionDTO.getLocation());
                    return competitionRepository.save(existingCompetition);
                })
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + competitionDTO.getId()));
    }

    public void deleteCompetition(Long competitionId) {
        competitionRepository.deleteById(competitionId);
    }
}