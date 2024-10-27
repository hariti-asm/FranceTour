package com.hariti.asmaa.FranceTour.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "stage_number", nullable = false)
    private Integer stageNumber;

    @Column(name = "start_location", nullable = false)
    private String startLocation;

    @Column(name = "end_location", nullable = false)
    private String endLocation;

    @Column(nullable = false)
    private double distance;

    @Column(nullable = false)
    private LocalDate date;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    @JsonIgnoreProperties({"stages", "hibernateLazyInitializer", "handler"})
    private Competition competition;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("stage")
    private Set<StageResult> results = new HashSet<>();

    // Constructors
    public Stage() {
        this.results = new HashSet<>();
    }

    public Stage(Long id, String name, Integer stageNumber, String startLocation,
                 String endLocation, double distance, LocalDate date,
                 Competition competition, Set<StageResult> results) {
        this.id = id;
        this.name = name;
        this.stageNumber = stageNumber;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.date = date;
        this.competition = competition;
        this.results = results != null ? results : new HashSet<>();
    }

    // Helper methods for bidirectional relationship
    public void addResult(StageResult result) {
        results.add(result);
        result.setStage(this);
    }

    public void removeResult(StageResult result) {
        results.remove(result);
        result.setStage(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage stage = (Stage) o;
        return Double.compare(stage.distance, distance) == 0
                && Objects.equals(id, stage.id)
                && Objects.equals(name, stage.name)
                && Objects.equals(stageNumber, stage.stageNumber)
                && Objects.equals(startLocation, stage.startLocation)
                && Objects.equals(endLocation, stage.endLocation)
                && Objects.equals(date, stage.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, stageNumber, startLocation, endLocation, distance, date);
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stageNumber=" + stageNumber +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", distance=" + distance +
                ", date=" + date +
                ", competitionId=" + (competition != null ? competition.getId() : null) +
                '}';
    }
}