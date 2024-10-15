package com.hariti.asmaa.FranceTour.Entities.Embeddebales;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;

    public Long getCyclistId() {
        return cyclistId;
    }

    public void setCyclistId(Long cyclistId) {
        this.cyclistId = cyclistId;
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }
}