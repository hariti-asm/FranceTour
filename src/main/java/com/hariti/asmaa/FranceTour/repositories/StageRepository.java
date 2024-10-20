package com.hariti.asmaa.FranceTour.repositories;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import com.hariti.asmaa.FranceTour.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

}
