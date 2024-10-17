package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
@Embeddable
public class StageResultId implements Serializable {
    private Long cyclistId;
    private Long stageId;

    public StageResultId() {
    }

    public StageResultId(Long cyclistId, Long stageId) {
        this.cyclistId = cyclistId;
        this.stageId = stageId;
    }

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