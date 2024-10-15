package com.hariti.asmaa.FranceTour.Entities.Embeddebales;

import javax.persistence.Embeddable;
import java.io.Serializable;

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
}