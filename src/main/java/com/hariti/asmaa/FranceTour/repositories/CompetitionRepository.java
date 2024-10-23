package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Competition findCompetitionByName(String name);

    List<Competition> findByEndDateBefore(LocalDate date);

}
