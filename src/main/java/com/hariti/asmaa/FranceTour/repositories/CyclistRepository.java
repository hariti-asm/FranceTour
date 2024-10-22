package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CyclistRepository extends JpaRepository<Cyclist, Long> {
    List<Cyclist> findByGeneralResults_CompetitionId(Long competitionId);
    Cyclist findCyclistByName(String name);

    void delete(Long cyclist);
}
