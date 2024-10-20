package com.hariti.asmaa.FranceTour;

import com.hariti.asmaa.FranceTour.config.JPAConfig;
import com.hariti.asmaa.FranceTour.entities.*;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import com.hariti.asmaa.FranceTour.repositories.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);

        CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
        CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);
        StageRepository stageRepository = context.getBean(StageRepository.class);
        StageResultRepository stageResultRepository = context.getBean(StageResultRepository.class);
        GeneralResultRepository generalResultRepository = context.getBean(GeneralResultRepository.class);

        TransactionTemplate transactionTemplate = context.getBean(TransactionTemplate.class);

        transactionTemplate.execute(status -> {
            try {
                // Create competition
                Competition competition = createCompetition(competitionRepository);

                // Create cyclists
                Cyclist cyclist1 = createCyclist(cyclistRepository, "John", "Doe", 28, "USA");
                Cyclist cyclist2 = createCyclist(cyclistRepository, "Jane", "Smith", 26, "UK");

                // Register cyclists in the competition
                registerCyclist(generalResultRepository, cyclist1, competition);
                registerCyclist(generalResultRepository, cyclist2, competition);

                // Display registered cyclists
                displayRegisteredCyclists(cyclistRepository, competition);

                // Unregister a cyclist
                unregisterCyclist(generalResultRepository, cyclist1, competition);
                displayRegisteredCyclists(cyclistRepository, competition);

                // Create stages
                Stage stage1 = createStage(stageRepository, competition, "Stage 1 - Paris to Lyon", 1, "Paris", "Lyon", LocalDate.of(2024, 7, 1), 200.5);
                Stage stage2 = createStage(stageRepository, competition, "Stage 2 - Lyon to Marseille", 2, "Lyon", "Marseille", LocalDate.of(2024, 7, 2), 180.3);

                // Record stage results
                recordStageResults(stageResultRepository, cyclist1, stage1, 2, "04:30:00");
                recordStageResults(stageResultRepository, cyclist2, stage1, 1, "04:35:00");
                recordStageResults(stageResultRepository, cyclist1, stage2, 2, "04:15:00");
                recordStageResults(stageResultRepository, cyclist2, stage2, 1, "04:10:00");

                // Update general results
                updateGeneralResults(generalResultRepository, competition, stageResultRepository);

                // Display results
                displayStageResults(stageResultRepository, stage1);
                displayStageResults(stageResultRepository, stage2);
                displayGeneralClassification(generalResultRepository, competition);

                // Generate competition report
                String report = generateCompetitionReport(competition.getId(), competitionRepository, cyclistRepository, stageResultRepository, generalResultRepository);
                System.out.println(report);

                // Get past competitions
                List<Competition> pastCompetitions = getPastCompetitions(competitionRepository);
                System.out.println("Past Competitions:");
                for (Competition comp : pastCompetitions) {
                    System.out.println(comp.getName() + " - " + comp.getStartDate() + " to " + comp.getEndDate());
                }

                // Get cyclist performance history
                List<GeneralResult> cyclistHistory = getCyclistPerformanceHistory(cyclist2.getId(), generalResultRepository);
                System.out.println("Cyclist Performance History:");
                for (GeneralResult result : cyclistHistory) {
                    System.out.println(result.getCompetition().getName() + " - Position: " + result.getFinalPosition());
                }

                return null;
            } catch (Exception e) {
                status.setRollbackOnly();
                e.printStackTrace();
                return null;
            }
        });

        context.close();
    }

    private static Competition createCompetition(CompetitionRepository competitionRepository) {
        Competition competition = new Competition();
        competition.setName("Tour de France 2024");
        competition.setLocation("France");
        competition.setStartDate(LocalDate.of(2024, 7, 1));
        competition.setEndDate(LocalDate.of(2024, 7, 21));
        return competitionRepository.save(competition);
    }

    private static Cyclist createCyclist(CyclistRepository cyclistRepository, String firstName, String lastName, int age, String nationality) {
        Cyclist cyclist = new Cyclist();
        cyclist.setFirstName(firstName);
        cyclist.setLastName(lastName);
        cyclist.setAge(age);
        cyclist.setNationality(nationality);
        return cyclistRepository.save(cyclist);
    }

    private static void registerCyclist(GeneralResultRepository generalResultRepository, Cyclist cyclist, Competition competition) {
        GeneralResult result = new GeneralResult();
        result.setCyclist(cyclist);
        result.setCompetition(competition);
        generalResultRepository.save(result);
    }

    private static void displayRegisteredCyclists(CyclistRepository cyclistRepository, Competition competition) {
        List<Cyclist> registeredCyclists = cyclistRepository.findByGeneralResults_CompetitionId(competition.getId());
        System.out.println("Registered cyclists for " + competition.getName() + ":");
        for (Cyclist cyclist : registeredCyclists) {
            System.out.println(" - " + cyclist.getFirstName() + " " + cyclist.getLastName());
        }
    }

    private static void unregisterCyclist(GeneralResultRepository generalResultRepository, Cyclist cyclist, Competition competition) {
        Optional<GeneralResult> resultToDelete = generalResultRepository.findByCyclistIdAndCompetitionId(cyclist.getId(), competition.getId());
        if (resultToDelete.isPresent()) {
            generalResultRepository.delete(resultToDelete.get());
            System.out.println("Unregistered " + cyclist.getFirstName() + " " + cyclist.getLastName() + " from the competition");
        } else {
            System.out.println("Could not find registration for " + cyclist.getFirstName());
        }
    }

    private static Stage createStage(StageRepository stageRepository, Competition competition, String name, int stageNumber, String startLocation, String endLocation, LocalDate date, double distance) {
        Stage stage = new Stage();
        stage.setName(name);
        stage.setCompetition(competition);
        stage.setStageNumber(stageNumber);
        stage.setStartLocation(startLocation);
        stage.setEndLocation(endLocation);
        stage.setDate(date);
        stage.setDistance(distance);
        return stageRepository.save(stage);
    }

    private static void recordStageResults(StageResultRepository stageResultRepository, Cyclist cyclist, Stage stage, int position, String time) {
        StageResult result = new StageResult();
        result.setCyclist(cyclist);
        result.setStage(stage);
        result.setPosition(position);
        result.setTime(time);
        stageResultRepository.save(result);
    }

    private static void updateGeneralResults(GeneralResultRepository generalResultRepository, Competition competition, StageResultRepository stageResultRepository) {
        List<Cyclist> cyclists = generalResultRepository.findCyclistsByCompetitionId(competition.getId());

        for (Cyclist cyclist : cyclists) {
            List<StageResult> stageResults = stageResultRepository.findByCyclistIdAndStageCompetitionId(cyclist.getId(), competition.getId());

            long totalTimeInSeconds = 0;
            for (StageResult result : stageResults) {
                LocalTime time = LocalTime.parse(result.getTime());
                totalTimeInSeconds += time.toSecondOfDay();
            }

            Optional<GeneralResult> generalResultOpt = generalResultRepository.findByCyclistIdAndCompetitionId(cyclist.getId(), competition.getId());
            if (generalResultOpt.isPresent()) {
                GeneralResult generalResult = generalResultOpt.get();
                generalResult.setTotalTime(LocalTime.ofSecondOfDay(totalTimeInSeconds).toString());
                generalResult.setFinalPosition(calculateRank(generalResultRepository, generalResult, competition));
                generalResultRepository.save(generalResult);
            }
        }
    }

    private static int calculateRank(GeneralResultRepository generalResultRepository, GeneralResult generalResult, Competition competition) {
        List<GeneralResult> allResults = generalResultRepository.findByCompetitionId(competition.getId());

        allResults.sort((a, b) -> {
            Long timeA = (long) LocalTime.parse(a.getTotalTime()).toSecondOfDay();
            Long timeB = (long) LocalTime.parse(b.getTotalTime()).toSecondOfDay();
            return Long.compare(timeA, timeB);
        });

        return allResults.indexOf(generalResult) + 1;
    }

    private static void displayStageResults(StageResultRepository stageResultRepository, Stage stage) {
        System.out.println("\nResults for " + stage.getName() + ":");
        List<StageResult> stageResults = stageResultRepository.findByStageIdWithCyclist(stage.getId());
        for (StageResult result : stageResults) {
            System.out.println(String.format("Cyclist: %s %s, Time: %s, Position: %d",
                    result.getCyclist().getFirstName(),
                    result.getCyclist().getLastName(),
                    result.getTime(),
                    result.getPosition()));
        }
    }

    private static void displayGeneralClassification(GeneralResultRepository generalResultRepository, Competition competition) {
        System.out.println("\nGeneral Classification:");
        List<GeneralResult> generalResults = generalResultRepository.findByCompetitionIdWithCyclist(competition.getId());
        for (GeneralResult result : generalResults) {
            String totalTime = result.getTotalTime() != null ? result.getTotalTime() : "N/A";
            int rank = result.getFinalPosition() != null ? result.getFinalPosition() : -1;
            System.out.println(String.format("Cyclist: %s %s, Total Time: %s, Rank: %d",
                    result.getCyclist().getFirstName(),
                    result.getCyclist().getLastName(),
                    totalTime,
                    rank));
        }
    }

    private static String generateCompetitionReport(Long competitionId, CompetitionRepository competitionRepository,
                                                    CyclistRepository cyclistRepository, StageResultRepository stageResultRepository,
                                                    GeneralResultRepository generalResultRepository) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        StringBuilder report = new StringBuilder();
        report.append("Rapport de compétition: ").append(competition.getName()).append("\n\n");

        // Participants
        List<Cyclist> participants = cyclistRepository.findByGeneralResults_CompetitionId(competitionId);
        report.append("Participants:\n");
        for (Cyclist cyclist : participants) {
            report.append("- ").append(cyclist.getFirstName()).append(" ").append(cyclist.getLastName()).append("\n");
        }
        report.append("\n");

        // Stage results
        Set<Stage> stages = competition.getStages();
        for (Stage stage : stages) {
            report.append("Résultats de l'étape: ").append(stage.getName()).append("\n");
            List<StageResult> stageResults = stageResultRepository.findByStageIdWithCyclist(stage.getId());
            for (StageResult result : stageResults) {
                report.append("- ").append(result.getCyclist().getFirstName()).append(" ")
                        .append(result.getCyclist().getLastName()).append(": ")
                        .append(result.getPosition()).append(", Time: ").append(result.getTime()).append("\n");
            }
            report.append("\n");
        }

        // General classification
        report.append("Classement général:\n");
        List<GeneralResult> generalResults = generalResultRepository.findByCompetitionIdWithCyclist(competitionId);
        for (GeneralResult result : generalResults) {
            report.append("- ").append(result.getCyclist().getFirstName()).append(" ")
                    .append(result.getCyclist().getLastName()).append(": ")
                    .append(result.getFinalPosition()).append(", Total Time: ").append(result.getTotalTime()).append("\n");
        }

        return report.toString();
    }

    private static List<Competition> getPastCompetitions(CompetitionRepository competitionRepository) {
        LocalDate today = LocalDate.now();
        return competitionRepository.findByEndDateBefore(today);
    }

    private static List<GeneralResult> getCyclistPerformanceHistory(Long cyclistId, GeneralResultRepository generalResultRepository) {
        return generalResultRepository.findByCyclistIdOrderByCompetitionStartDateDesc(cyclistId);
    }
}