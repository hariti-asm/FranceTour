package com.hariti.asmaa.FranceTour;

import com.hariti.asmaa.FranceTour.config.JPAConfig;
import com.hariti.asmaa.FranceTour.repositories.TeamRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
        TeamRepository teamRepository = context.getBean(TeamRepository.class);

        // Fetch and print teams directly without transaction template
        System.out.println("Showing teams directly from repository:");
        teamRepository.findAll().forEach(team -> System.out.println(team.getName()));

        context.close();
    }
}
