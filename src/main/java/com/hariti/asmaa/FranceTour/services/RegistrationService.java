package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import com.hariti.asmaa.FranceTour.repositories.GeneralResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final CyclistRepository cyclistRepository;
    private final CompetitionRepository competitionRepository;
    private final GeneralResultRepository generalResultRepository;

    @Autowired
    public RegistrationService(CyclistRepository cyclistRepository,
                               CompetitionRepository competitionRepository,
                               GeneralResultRepository generalResultRepository) {
        this.cyclistRepository = cyclistRepository;
        this.competitionRepository = competitionRepository;
        this.generalResultRepository = generalResultRepository;
    }

    @Transactional
    public void registerCyclistForCompetition(Long cyclistId, Long competitionId) {
        Optional<Cyclist> cyclistOpt = cyclistRepository.findById(cyclistId);
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);

        if (cyclistOpt.isPresent() && competitionOpt.isPresent()) {
            Cyclist cyclist = cyclistOpt.get();
            Competition competition = competitionOpt.get();

            if (!isAlreadyRegistered(cyclist, competition)) {
                GeneralResult generalResult = new GeneralResult();
                generalResult.setCyclist(cyclist);
                generalResult.setCompetition(competition);
                generalResultRepository.save(generalResult);
            }
        } else {
            throw new IllegalArgumentException("Cyclist or Competition not found");
        }
    }

    @Transactional
    public void unregisterCyclistFromCompetition(Long cyclistId, Long competitionId) {
        Optional<GeneralResult> resultOpt = generalResultRepository.findByCyclistIdAndCompetitionId(cyclistId, competitionId);
        resultOpt.ifPresent(generalResultRepository::delete);
    }

    public List<Cyclist> getRegisteredCyclistsForCompetition(Long competitionId) {
        return cyclistRepository.findByGeneralResults_CompetitionId(competitionId);
    }

    private boolean isAlreadyRegistered(Cyclist cyclist, Competition competition) {
        return generalResultRepository.existsByCyclistAndCompetition(cyclist, competition);
    }
}