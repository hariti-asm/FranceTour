package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;

    // Constructors
    public GeneralResultId() {}

    public GeneralResultId(Long cyclistId, Long competitionId) {
        this.cyclistId = cyclistId;
        this.competitionId = competitionId;
    }

    // Getters and setters
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

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResultId that = (GeneralResultId) o;
        return Objects.equals(cyclistId, that.cyclistId) &&
                Objects.equals(competitionId, that.competitionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cyclistId, competitionId);
    }
}