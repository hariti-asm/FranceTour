package com.hariti.asmaa.FranceTour.services;

import com.hariti.asmaa.FranceTour.entities.Stage;
import com.hariti.asmaa.FranceTour.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StageService {

    private final StageRepository stageRepository;

    @Autowired
    public StageService(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    public Stage saveStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Page<Stage> findAllStages(Pageable pageable) {
        return stageRepository.findAll(pageable);
    }

    public Optional<Stage> findStageById(Long id) {
        return stageRepository.findById(id);
    }

    public Stage updateStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }
}
