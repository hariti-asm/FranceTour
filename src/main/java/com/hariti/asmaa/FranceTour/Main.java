package com.hariti.asmaa.FranceTour;

import com.hariti.asmaa.FranceTour.Config.JPAConfig;
import com.hariti.asmaa.FranceTour.Entities.Competition;
import com.hariti.asmaa.FranceTour.Services.CompetitionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
        CompetitionService competitionService = context.getBean(CompetitionService.class);
        List<Competition> competitions = competitionService.findAllCompetitions();
        competitions.forEach(System.out::println);
    }


}