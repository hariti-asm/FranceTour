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
import org.springframework.stereotype.Service;

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
}