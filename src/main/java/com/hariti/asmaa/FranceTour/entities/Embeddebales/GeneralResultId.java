package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter
@Setter
@Embeddable
public class GeneralResultId implements Serializable {
    private Long cyclistId;
    private Long competitionId;

    public GeneralResultId() {
    }

    public GeneralResultId(Long cyclistId, Long competitionId) {
        this.cyclistId = cyclistId;
        this.competitionId = competitionId;
    }



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