package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import com.hariti.asmaa.FranceTour.repositories.GeneralResultRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralResultService {

    @Autowired
    private GeneralResultRepository generalResultRepository;

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Transactional
    public GeneralResult createGeneralResult(Long cyclistId, Long competitionId, String totalTime, Integer finalPosition) {
        Cyclist cyclist = cyclistRepository.findById(cyclistId)
                .orElseThrow(() -> new EntityNotFoundException("Cyclist not found"));

        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));

        GeneralResult generalResult = new GeneralResult();
        generalResult.setCyclist(cyclist);
        generalResult.setCompetition(competition);
        generalResult.setTotalTime(totalTime);
        generalResult.setFinalPosition(finalPosition);

        return generalResultRepository.save(generalResult);
    }

    public Optional<GeneralResult> findByCyclistAndCompetition(Long cyclistId, Long competitionId) {
        return generalResultRepository.findByCyclistIdAndCompetitionId(cyclistId, competitionId);
    }

    @Transactional
    public void deleteGeneralResult(Long cyclistId, Long competitionId) {
        if (!generalResultRepository.existsByCyclistAndCompetition(
                cyclistRepository.findById(cyclistId).orElseThrow(() -> new EntityNotFoundException("Cyclist not found")),
                competitionRepository.findById(competitionId).orElseThrow(() -> new EntityNotFoundException("Competition not found"))
        )) {
            throw new EntityNotFoundException("General result not found");
        }
        generalResultRepository.deleteByCyclistIdAndCompetitionId(cyclistId, competitionId);
    }

    @Transactional
    public void updateGeneralResult(Long cyclistId, Long competitionId, String totalTime, Integer finalPosition) {
        generalResultRepository.updateGeneralResult(cyclistId, competitionId, totalTime, finalPosition);
    }

    public List<GeneralResult> findAllByCompetition(Long competitionId) {
        return generalResultRepository.findByCompetitionId(competitionId);
    }

    public List<Cyclist> findCyclistsByCompetition(Long competitionId) {
        return generalResultRepository.findCyclistsByCompetitionId(competitionId);
    }

    public Long calculateFinalPosition(Long cyclistId, Long competitionId) {
        return generalResultRepository.calculateFinalPosition(cyclistId, competitionId);
    }

    public Long calculateTotalTimeInSeconds(Long cyclistId, Long competitionId) {
        return generalResultRepository.calculateTotalSecondsForCyclist(cyclistId, competitionId);
    }

    public Page<GeneralResult> findAllGeneralResults(Pageable pageable) {
        return generalResultRepository.findAll(pageable);
    }

    public GeneralResult createGeneralResult(GeneralResult generalResult) {
        return generalResultRepository.save(generalResult);
    }

}
