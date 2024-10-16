package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StageResultId implements Serializable {
    private Long cyclistId;
    private Long stageId;

    // Constructors
    public StageResultId() {}

    public StageResultId(Long cyclistId, Long stageId) {
        this.cyclistId = cyclistId;
        this.stageId = stageId;
    }

    // Getters and Setters
    public Long getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Long cyclistId) {
        this.cyclistId = cyclistId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageResultId that = (StageResultId) o;
        return Objects.equals(cyclistId, that.cyclistId) &&
                Objects.equals(stageId, that.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, stageId);
    }
}