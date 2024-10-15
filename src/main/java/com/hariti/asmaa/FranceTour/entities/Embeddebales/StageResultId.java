package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StageResultId implements Serializable {
    private Long cyclistId;
    private Long stageId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StageResultId that = (StageResultId) o;
        return Objects.equals(cyclistId, that.cyclistId) && Objects.equals(stageId, that.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, stageId);
    }
}