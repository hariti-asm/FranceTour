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

    public Optional<Competition> findCompetitionByName(String name) {

      Optional <Competition> competition  = competitionRepository.findCompetitionByName(name.toLowerCase());
      return competition;

    }
    public  Optional<Competition> findCompetitionById(long id) {
        return competitionRepository.findById(id);

    }

    public List<Competition> findAllCompetitions() {
        return null;
    }

    public Page<Competition> findAllCompetitions(Pageable pageable) {
        return null;
    }

    public Competition saveCompetition(Competition competition) {
        if(competition==null){
            throw new IllegalArgumentException("Competition can't be created");
        }
        if(competition.getStartDate().isAfter(competition.getEndDate())){
            throw new IllegalArgumentException("Competition start date can't be after end date");
        }
        Optional<Competition> competition2 = competitionRepository.findCompetitionByName(competition.getName());
        if(competition2.isPresent()){
            throw new IllegalArgumentException("Competition already exists");
        }

        return competitionRepository.save(competition);
    }

    public Competition updateCompetition(Competition competition) {
        return null;
    }

    public void deleteCompetition(Long competitionId) {
        competitionRepository.deleteById(competitionId);
    }
}
