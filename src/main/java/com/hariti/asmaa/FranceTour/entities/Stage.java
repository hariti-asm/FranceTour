package com.hariti.asmaa.FranceTour.entities;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.StageResult;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Data
@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double distance;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StageResult> results = new HashSet<>();

    public Stage(Long id, String name, double distance, LocalDate date, Competition competition, Set<StageResult> results) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.date = date;
        this.competition = competition;
        this.results = results;
    }

    public Stage() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stage stage = (Stage) o;
        return Double.compare(distance, stage.distance) == 0 && Objects.equals(id, stage.id) && Objects.equals(name, stage.name) && Objects.equals(date, stage.date) && Objects.equals(competition, stage.competition) && Objects.equals(results, stage.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, distance, date, competition, results);
    }

}
