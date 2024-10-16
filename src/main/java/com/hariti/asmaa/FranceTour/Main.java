package com.hariti.asmaa.FranceTour;

import com.hariti.asmaa.FranceTour.config.JPAConfig;
import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.services.CompetitionService;
import com.hariti.asmaa.FranceTour.services.CyclistService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);

        CompetitionService competitionService = context.getBean(CompetitionService.class);
        CyclistService cyclistService = context.getBean(CyclistService.class);

        Competition newCompetition = new Competition();
        newCompetition.setName("Tour de France");
        newCompetition.setLocation("France");
        newCompetition.setStartDate(LocalDate.of(2024, 7, 1));
        newCompetition.setEndDate(LocalDate.of(2024, 7, 21));

        competitionService.saveCompetition(newCompetition);
        System.out.println("Competition saved: " + newCompetition.getName());
        System.out.println("All Competitions:");
        competitionService.findAllCompetitions().forEach(competition -> {
            System.out.println(" - " + competition.getName());
        });

        System.out.println("Showing competition by id");
        Optional<Competition> competition = competitionService.findCompetitionById(1);
        if (competition.isPresent()) {
            System.out.println("Found Competition: " + competition.get().getName());
        } else {
            System.out.println("Competition not found.");
        }


    }}
