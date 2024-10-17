package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, Long> {
    Optional<GeneralResult> findByCyclistIdAndCompetitionId(Long cyclistId, Long competitionId);
    boolean existsByCyclistAndCompetition(Cyclist cyclist, Competition competition);
    void deleteByCyclistIdAndCompetitionId(Long cyclistId, Long competitionId);

}
