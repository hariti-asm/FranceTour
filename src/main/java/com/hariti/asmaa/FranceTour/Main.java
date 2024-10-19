    package com.hariti.asmaa.FranceTour;

    import com.hariti.asmaa.FranceTour.config.JPAConfig;
    import com.hariti.asmaa.FranceTour.entities.*;
    import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
    import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
    import com.hariti.asmaa.FranceTour.repositories.*;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.annotation.AnnotationConfigApplicationContext;

    import java.time.LocalDate;
    import java.time.LocalTime;
    import java.util.List;
    import java.util.Optional;

    public class Main {
        public static void main(String[] args) {
            ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);

            CompetitionRepository competitionRepository = context.getBean(CompetitionRepository.class);
            CyclistRepository cyclistRepository = context.getBean(CyclistRepository.class);
            StageRepository stageRepository = context.getBean(StageRepository.class);
            StageResultRepository stageResultRepository = context.getBean(StageResultRepository.class);
            GeneralResultRepository generalResultRepository = context.getBean(GeneralResultRepository.class);

            // Create competition
            Competition competition = new Competition();
            competition.setName("Tour de France 2024");
            competition.setLocation("France");
            competition.setStartDate(LocalDate.of(2024, 7, 1));
            competition.setEndDate(LocalDate.of(2024, 7, 21));
            competition = competitionRepository.save(competition);

            // Create cyclists
            Cyclist cyclist1 = new Cyclist();
            cyclist1.setFirstName("John");
            cyclist1.setLastName("Doe");
            cyclist1.setAge(28);
            cyclist1.setNationality("USA");
            cyclist1 = cyclistRepository.save(cyclist1);

            Cyclist cyclist2 = new Cyclist();
            cyclist2.setFirstName("Jane");
            cyclist2.setLastName("Smith");
            cyclist2.setAge(26);
            cyclist2.setNationality("UK");
            cyclist2 = cyclistRepository.save(cyclist2);

            GeneralResult result1 = new GeneralResult();
            result1.setCyclist(cyclist1);
            result1.setCompetition(competition);
            generalResultRepository.save(result1);

            GeneralResult result2 = new GeneralResult();
            result2.setCyclist(cyclist2);
            result2.setCompetition(competition);
            generalResultRepository.save(result2);

            System.out.println("Cyclists registered for the competition");

            // Test getCyclistsInCompetition
            List<Cyclist> registeredCyclists = cyclistRepository.findByGeneralResults_CompetitionId(competition.getId());
            System.out.println("Registered cyclists for " + competition.getName() + ":");
            for (Cyclist cyclist : registeredCyclists) {
                System.out.println(" - " + cyclist.getFirstName() + " " + cyclist.getLastName());
            }

            // Test unregistering a cyclist
            Optional<GeneralResult> resultToDelete = generalResultRepository.findByCyclistIdAndCompetitionId(cyclist1.getId(), competition.getId());
            if (resultToDelete.isPresent()) {
                generalResultRepository.delete(resultToDelete.get());
                System.out.println("Unregistered cyclist1 from the competition");
            } else {
                System.out.println("Could not find registration for cyclist1");
            }

            // Check registered cyclists again
            registeredCyclists = cyclistRepository.findByGeneralResults_CompetitionId(competition.getId());
            System.out.println("Updated registered cyclists for " + competition.getName() + ":");
            for (Cyclist cyclist : registeredCyclists) {
                System.out.println(" - " + cyclist.getFirstName() + " " + cyclist.getLastName());
            }

            // Create stages
            Stage stage1 = new Stage();
            stage1.setName("Stage 1 - Paris to Lyon");
            stage1.setCompetition(competition);
            stage1.setStageNumber(1);
            stage1.setStartLocation("Paris");
            stage1.setEndLocation("Lyon");
            stage1.setDate(LocalDate.of(2024, 7, 1));
            stage1.setDistance(200.5);
            stage1 = stageRepository.save(stage1);

            Stage stage2 = new Stage();
            stage2.setName("Stage 2 - Lyon to Marseille");
            stage2.setCompetition(competition);
            stage2.setStageNumber(2);
            stage2.setStartLocation("Lyon");
            stage2.setEndLocation("Marseille");
            stage2.setDate(LocalDate.of(2024, 7, 2));
            stage2.setDistance(180.3);
            stage2 = stageRepository.save(stage2);
            System.out.println("Testing stage rankings trigger...");

            StageResult result1Stage1 = new StageResult();
            result1Stage1.setCyclist(cyclist1);
            result1Stage1.setStage(stage1);
            result1Stage1.setPosition(2);
            result1Stage1.setTime(String.valueOf(LocalTime.of(4, 30, 0)));
            stageResultRepository.save(result1Stage1);

            StageResult result2Stage1 = new StageResult();
            result2Stage1.setCyclist(cyclist2);
            result2Stage1.setStage(stage1);
            result2Stage1.setPosition(1);
            result2Stage1.setTime(String.valueOf(LocalTime.of(4, 35, 0)));
            stageResultRepository.save(result2Stage1);

            StageResult result1Stage2 = new StageResult();
            result1Stage2.setCyclist(cyclist1);
            result1Stage2.setStage(stage2);
            result1Stage2.setTime(String.valueOf(LocalTime.of(4, 15, 0)));
            stageResultRepository.save(result1Stage2);

            StageResult result2Stage2 = new StageResult();
            result2Stage2.setCyclist(cyclist2);
            result2Stage2.setStage(stage2);
            result2Stage2.setTime(String.valueOf(LocalTime.of(4, 10, 0)));
            stageResultRepository.save(result2Stage2);

            System.out.println("\nStage 1 Results:");
            List<StageResult> stage1Results = stageResultRepository.findByStageIdWithCyclist(stage1.getId());
            for (StageResult result : stage1Results) {
                System.out.println(String.format("Cyclist: %s %s, Time: %s, Position: %d",
                        result.getCyclist().getFirstName(),
                        result.getCyclist().getLastName(),
                        result.getTime(),
                        result.getPosition()));
            }

            System.out.println("\nStage 2 Results:");
            List<StageResult> stage2Results = stageResultRepository.findByStageIdWithCyclist(stage2.getId());
            for (StageResult result : stage2Results) {
                System.out.println(String.format("Cyclist: %s %s, Time: %s, Position: %d",
                        result.getCyclist().getFirstName(),
                        result.getCyclist().getLastName(),
                        result.getTime(),
                        result.getPosition()));
            }

            System.out.println("\nGeneral Classification:");
            List<GeneralResult> generalResults = generalResultRepository.findByCompetitionIdWithCyclist(competition.getId());

            for (GeneralResult result : generalResults) {
                System.out.println(String.format("Cyclist: %s %s, Total Time: %s, Rank: %d",
                        result.getCyclist().getFirstName(),
                        result.getCyclist().getLastName(),
                        result.getTotalTime(),
                        result.getFinalPosition()));
            }
        }
        }
