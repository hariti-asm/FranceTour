package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import com.hariti.asmaa.FranceTour.entities.Competition;
import com.hariti.asmaa.FranceTour.entities.Cyclist;
import jakarta.persistence.*;
import java.time.Duration;

@Entity
public class GeneralResult {
    @EmbeddedId
    private GeneralResultId id;

    @ManyToOne
    @JoinColumn(name = "cyclist_id", insertable = false, updatable = false)
    private Cyclist cyclist;

    @ManyToOne
    @JoinColumn(name = "competition_id", insertable = false, updatable = false)
    private Competition competition;

    private Duration generalTime;
    private Integer generalRank;

    // Getters and setters

    public GeneralResultId getId() {
        return id;
    }

    public void setId(GeneralResultId id) {
        this.id = id;
    }

    public Cyclist getCyclist() {
        return cyclist;
    }

    public void setCyclist(Cyclist cyclist) {
        this.cyclist = cyclist;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Duration getGeneralTime() {
        return generalTime;
    }

    public void setGeneralTime(Duration generalTime) {
        this.generalTime = generalTime;
    }

    public Integer getGeneralRank() {
        return generalRank;
    }

    public void setGeneralRank(Integer generalRank) {
        this.generalRank = generalRank;
    }
}