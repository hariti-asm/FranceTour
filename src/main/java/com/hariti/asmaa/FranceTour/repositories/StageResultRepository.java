package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StageResultRepository extends JpaRepository<StageResult, Long> {
    List<StageResult> findByStageId(Long id);
    @Query("SELECT sr.cyclist.id, SEC_TO_TIME(SUM(TIME_TO_SEC(sr.time))) as totalTime " +
            "FROM StageResult sr " +
            "JOIN sr.stage s " +
            "WHERE s.competition.id = :competitionId " +
            "GROUP BY sr.cyclist.id " +
            "ORDER BY sr.time")
    List<Object[]> calculateTotalTimesByCompetition(Long competitionId);
}
