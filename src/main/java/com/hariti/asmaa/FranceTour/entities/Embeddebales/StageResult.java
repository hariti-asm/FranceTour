package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Stage;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stage_id", nullable = false)
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
    public StageResult(Long id, Cyclist cyclist, Stage stage, Integer position, String time) {
        this.id = id;
        this.cyclist = cyclist;
        this.stage = stage;
        this.position = position;
        this.time = time;
        this.setStage(stage); // This will set both stage and stageNumber
    }

    // Modified setter for stage to automatically set stageNumber
    public void setStage(Stage stage) {
        this.stage = stage;
        if (stage != null) {
            this.stageNumber = stage.getStageNumber();
        }
    }

    @PrePersist
    @PreUpdate
    private void ensureStageNumber() {
        if (this.stageNumber == null && this.stage != null) {
            this.stageNumber = this.stage.getStageNumber();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageResult that = (StageResult) o;
        return Objects.equals(id, that.id) && Objects.equals(cyclist, that.cyclist) && Objects.equals(stage, that.stage) && Objects.equals(stageNumber, that.stageNumber) && Objects.equals(position, that.position) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cyclist, stage, stageNumber, position, time);
    }
}