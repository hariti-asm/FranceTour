package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import com.hariti.asmaa.FranceTour.repositories.StageRepository;
import com.hariti.asmaa.FranceTour.repositories.StageResultRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StageResultService {

    @Autowired
    private StageResultRepository stageResultRepository;

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private StageRepository stageRepository;

    @Transactional
    public StageResult createStageResult(Long cyclistId, Long stageId, Integer position, String time) {
        Cyclist cyclist = cyclistRepository.findById(cyclistId)
                .orElseThrow(() -> new EntityNotFoundException("Cyclist not found"));

        Stage stage = stageRepository.findById(stageId)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));

        StageResult stageResult = new StageResult();
        stageResult.setCyclist(cyclist);
        stageResult.setStage(stage);
        stageResult.setPosition(position);
        stageResult.setTime(time);

        return stageResultRepository.save(stageResult);
    }

    public Page<StageResult> findAllStageResults(Pageable pageable) {
        return stageResultRepository.findAll(pageable);
    }

    public Optional<StageResult> findByStageAndCyclist(Long stageId, Long cyclistId) {
        return stageResultRepository.findByStageIdAndCyclistId(stageId, cyclistId);
    }

    @Transactional
    public void deleteStageResult(Long cyclistId, Long stageId) {
        StageResult stageResult = stageResultRepository.findByStageIdAndCyclistId(stageId, cyclistId)
                .orElseThrow(() -> new EntityNotFoundException("Stage result not found"));
        stageResultRepository.delete(stageResult);
    }
}