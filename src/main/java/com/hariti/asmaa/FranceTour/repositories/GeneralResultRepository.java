package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, Long> {
    Optional<GeneralResult> findByCyclistIdAndCompetitionId(Long cyclistId, Long competitionId);
    boolean existsByCyclistAndCompetition(Cyclist cyclist, Competition competition);
    void deleteByCyclistIdAndCompetitionId(Long cyclistId, Long competitionId);

    List<GeneralResult> findByCompetitionId(Long id);

    @Query("SELECT gr FROM GeneralResult gr JOIN FETCH gr.cyclist WHERE gr.competition.id = :competitionId")
    List<GeneralResult> findByCompetitionIdWithCyclist(@Param("competitionId") Long competitionId);

    @Modifying
    @Query("UPDATE GeneralResult gr SET gr.totalTime = :totalTime, gr.finalPosition = :finalPosition " +
            "WHERE gr.cyclist.id = :cyclistId AND gr.competition.id = :competitionId")
    void updateGeneralResult(Long cyclistId, Long competitionId, String totalTime, Integer finalPosition);

    @Query("SELECT SUM(CAST(SUBSTRING(sr.time, 1, 2) AS int) * 3600 + " +
            "CAST(SUBSTRING(sr.time, 4, 2) AS int) * 60 + " +
            "CAST(SUBSTRING(sr.time, 7, 2) AS int)) " +
            "FROM StageResult sr WHERE sr.cyclist.id = :cyclistId AND sr.stage.competition.id = :competitionId")
    Long calculateTotalSecondsForCyclist(Long cyclistId, Long competitionId);

    @Query("SELECT COUNT(gr) + 1 FROM GeneralResult gr WHERE gr.competition.id = :competitionId AND " +
            "(CAST(SUBSTRING(gr.totalTime, 1, 2) AS int) * 3600 + " +
            "CAST(SUBSTRING(gr.totalTime, 4, 2) AS int) * 60 + " +
            "CAST(SUBSTRING(gr.totalTime, 7, 2) AS int)) < " +
            "(SELECT SUM(CAST(SUBSTRING(sr.time, 1, 2) AS int) * 3600 + " +
            "CAST(SUBSTRING(sr.time, 4, 2) AS int) * 60 + " +
            "CAST(SUBSTRING(sr.time, 7, 2) AS int)) " +
            "FROM StageResult sr WHERE sr.cyclist.id = :cyclistId AND sr.stage.competition.id = :competitionId)")
    Long calculateFinalPositionForCyclist(Long cyclistId, Long competitionId);
}