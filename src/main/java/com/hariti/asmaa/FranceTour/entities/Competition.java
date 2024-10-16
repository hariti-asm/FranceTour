package com.hariti.asmaa.FranceTour.entities;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Stage> stages = new HashSet<>();

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GeneralResult> generalResults = new HashSet<>();

    // Constructors
    public Competition() {}

    public Competition( Long id ,String name, LocalDate startDate, LocalDate endDate, String location) {
        this.name = name;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    // You might want to add methods to manage the relationships
    public void addStage(Stage stage) {
        stages.add(stage);
        stage.setCompetition(this);
    }

    public void removeStage(Stage stage) {
        stages.remove(stage);
        stage.setCompetition(null);
    }

    public void addGeneralResult(GeneralResult result) {
        generalResults.add(result);
        result.setCompetition(this);
    }

    public void removeGeneralResult(GeneralResult result) {
        generalResults.remove(result);
        result.setCompetition(null);
    }
}