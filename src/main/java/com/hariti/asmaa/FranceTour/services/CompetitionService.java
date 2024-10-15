package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
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

    public Optional<Competition> findCompetitionById(long id) {
        return competitionRepository.findById(id);
    }

    public List<Competition> findAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Page<Competition> findAllCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }

    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        if(competition ==null) { throw new NullPointerException("Competition is null"); }
        if(!competitionRepository.existsById(competition.getId())) {
            throw new NullPointerException("Competition not found");
        }
        return competitionRepository.save(competition);
    }

    public void deleteCompetition(long id) {
        if(!competitionRepository.existsById(id)) {
            throw new NullPointerException("Competition not found");
        }
        competitionRepository.deleteById(id);
    }
}
