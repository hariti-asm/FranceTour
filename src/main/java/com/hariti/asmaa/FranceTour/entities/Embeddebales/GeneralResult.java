package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "general_results")
public class GeneralResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cyclist_id", nullable = false)
    private Cyclist cyclist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @Column(name = "final_position")
    private Integer finalPosition;

    @Column(name = "total_time")
    private String totalTime;

    public GeneralResult(Long id, Cyclist cyclist, Competition competition, Integer finalPosition, String totalTime) {
        this.id = id;
        this.cyclist = cyclist;
        this.competition = competition;
        this.finalPosition = finalPosition;
        this.totalTime = totalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResult that = (GeneralResult) o;
        return Objects.equals(id, that.id) && Objects.equals(cyclist, that.cyclist) && Objects.equals(competition, that.competition) && Objects.equals(finalPosition, that.finalPosition) && Objects.equals(totalTime, that.totalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cyclist, competition, finalPosition, totalTime);
    }

    // Constructors, equals, hashCode, and toString methods...
}