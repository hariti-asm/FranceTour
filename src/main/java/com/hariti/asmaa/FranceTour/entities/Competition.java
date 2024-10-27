package com.hariti.asmaa.FranceTour.entities;

import com.hariti.asmaa.FranceTour.entities.Embeddebales.GeneralResult;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Setter
@Getter
@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Stage> stages = new HashSet<>();

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GeneralResult> generalResults = new HashSet<>();

    public Competition(String name, LocalDate startDate, LocalDate endDate, String location, Set<Stage> stages, Set<GeneralResult> generalResults) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.stages = stages;
        this.generalResults = generalResults;
    }

    public Competition() {
    }

    public void addGeneralResult(GeneralResult result) {
        generalResults.add(result);
        result.setCompetition(this);
    }

    public void removeGeneralResult(GeneralResult result) {
        generalResults.remove(result);
        result.setCompetition(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) &&
                Objects.equals(location, that.location) && Objects.equals(stages, that.stages) &&
                Objects.equals(generalResults, that.generalResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, location, stages, generalResults);
    }
}
