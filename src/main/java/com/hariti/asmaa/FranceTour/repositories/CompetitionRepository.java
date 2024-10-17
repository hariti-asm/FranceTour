package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Optional<Competition> findCompetitionByName(String name);
}
