package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
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


    public Competition saveCompetition(Competition competition) {
        if (competition == null) {
            throw new IllegalArgumentException("Competition can't be created");
        }
        if (competition.getStartDate().isAfter(competition.getEndDate())) {
            throw new IllegalArgumentException("Competition start date can't be after end date");
        }
     Competition competition2 = competitionRepository.findCompetitionByName(competition.getName());
        if (competition2!=null) {
            throw new IllegalArgumentException("Competition already exists");
        }

        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return competitionRepository.findById(competition.getId())
                .map(existingCompetition -> {
                    existingCompetition.setName(competition.getName());
                    existingCompetition.setStartDate(competition.getStartDate());
                    existingCompetition.setEndDate(competition.getEndDate());
                    existingCompetition.setLocation(competition.getLocation());
                    return competitionRepository.save(existingCompetition);
                })
                .orElseThrow(() -> new RuntimeException("Competition not found with id: " + competition.getId()));
    }

    public void deleteCompetition(Long competitionId) {
        competitionRepository.deleteById(competitionId);
    }
}
