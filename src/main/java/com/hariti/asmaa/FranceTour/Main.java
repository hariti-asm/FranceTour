package com.hariti.asmaa.FranceTour;

import com.hariti.asmaa.FranceTour.config.JPAConfig;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.repositories.CompetitionRepository;
import com.hariti.asmaa.FranceTour.repositories.CyclistRepository;
import com.hariti.asmaa.FranceTour.repositories.GeneralResultRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);

        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
        CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);
        GeneralResultRepository generalResultRepository = context.getBean(GeneralResultRepository.class);

        Competition newCompetition = new Competition();
        newCompetition.setName("Tour de France 2024");
        newCompetition.setLocation("France");
        newCompetition.setStartDate(LocalDate.of(2024, 7, 1));
        newCompetition.setEndDate(LocalDate.of(2024, 7, 21));
        competitionRepository.save(newCompetition);
        System.out.println("Competition saved: " + newCompetition.getName());

        Cyclist cyclist1 = new Cyclist();
        cyclist1.setFirstName("John");
        cyclist1.setLastName("Doe");
        cyclist1.setAge(28);
        cyclist1.setNationality("USA");
        cyclistRepository.save(cyclist1);

        Cyclist cyclist2 = new Cyclist();
        cyclist2.setFirstName("Jane");
        cyclist2.setLastName("Smith");
        cyclist2.setAge(26);
        cyclist2.setNationality("UK");
        cyclistRepository.save(cyclist2);

        System.out.println("Cyclists saved");

        // Register cyclists for the competition
        GeneralResult result1 = new GeneralResult();
        result1.setCyclist(cyclist1);
        result1.setCompetition(newCompetition);
        generalResultRepository.save(result1);

        GeneralResult result2 = new GeneralResult();
        result2.setCyclist(cyclist2);
        result2.setCompetition(newCompetition);
        generalResultRepository.save(result2);

        System.out.println("Cyclists registered for the competition");

        // Test getCyclistsInCompetition
        List<Cyclist> registeredCyclists = cyclistRepository.findByGeneralResults_CompetitionId(newCompetition.getId());
        System.out.println("Registered cyclists for " + newCompetition.getName() + ":");
        for (Cyclist cyclist : registeredCyclists) {
            System.out.println(" - " + cyclist.getFirstName() + " " + cyclist.getLastName());
        }

        // Test unregistering a cyclist
        Optional<GeneralResult> resultToDelete = generalResultRepository.findByCyclistIdAndCompetitionId(cyclist1.getId(), newCompetition.getId());
        if (resultToDelete.isPresent()) {
            generalResultRepository.delete(resultToDelete.get());
            System.out.println("Unregistered cyclist1 from the competition");
        } else {
            System.out.println("Could not find registration for cyclist1");
        }

        // Check registered cyclists again
        registeredCyclists = cyclistRepository.findByGeneralResults_CompetitionId(newCompetition.getId());
        System.out.println("Updated registered cyclists for " + newCompetition.getName() + ":");
        for (Cyclist cyclist : registeredCyclists) {
            System.out.println(" - " + cyclist.getFirstName() + " " + cyclist.getLastName());
        }
    }
}