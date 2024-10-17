package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Stage; // Ensure you import the Stage entity
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "stage_results")
public class StageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cyclist_id", nullable = false)
    private Cyclist cyclist;

    @ManyToOne(fetch = FetchType.LAZY) // Establishing a ManyToOne relationship with Stage
    @JoinColumn(name = "stage_id", nullable = false) // This should correspond to a foreign key in the stage_results table
    private Stage stage;

    @Column(name = "stage_number", nullable = false)
    private Integer stageNumber;

    @Column(name = "position")
    private Integer position;

    @Column(name = "time")
    private String time;

    // Default constructor
    public StageResult() {}

    // Constructor with all fields
    public StageResult(Long id, Cyclist cyclist, Stage stage, Integer stageNumber, Integer position, String time) {
        this.id = id;
        this.cyclist = cyclist;
        this.stage = stage; // Add the Stage reference
        this.stageNumber = stageNumber;
        this.position = position;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageResult that = (StageResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(stageNumber, that.stageNumber) &&
                Objects.equals(position, that.position) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stageNumber, position, time);
    }

    @Override
    public String toString() {
        return "StageResult{" +
                "id=" + id +
                ", stageNumber=" + stageNumber +
                ", position=" + position +
                ", time='" + time + '\'' +
                '}';
    }
}
