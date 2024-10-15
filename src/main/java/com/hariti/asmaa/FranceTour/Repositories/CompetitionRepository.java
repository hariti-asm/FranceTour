package com.hariti.asmaa.FranceTour.Repositories;

import com.hariti.asmaa.FranceTour.Config.JPAConfig;
import com.hariti.asmaa.FranceTour.Entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

}
