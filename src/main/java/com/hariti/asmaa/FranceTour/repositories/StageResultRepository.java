package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StageResultRepository extends JpaRepository<StageResult, Long> {
    @Query("SELECT sr FROM StageResult sr JOIN FETCH sr.cyclist WHERE sr.stage.id = :stageId")
    List<StageResult> findByStageIdWithCyclist(@Param("stageId") Long stageId);

    List<StageResult> findByCyclistIdAndStageCompetitionId(Long cyclistId, Long competitionId);

    Optional<StageResult> findByStageIdAndCyclistId(Long stageId, Long cyclistId);
}
