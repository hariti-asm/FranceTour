package com.hariti.asmaa.FranceTour.Services;

import com.hariti.asmaa.FranceTour.Entities.Stage;
import com.hariti.asmaa.FranceTour.Repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
 public List<Stage> getAllStages() {
    return stageRepository.findAll();
 }
 public Optional<Stage> getStageById(Long id) {
    return  stageRepository.findById(id);
 }
 public void deleteStageById(Long id) {
    stageRepository.deleteById(id);
 }
 public Stage updateStage(Stage stage) {
    return stageRepository.save(stage);
 }
}
