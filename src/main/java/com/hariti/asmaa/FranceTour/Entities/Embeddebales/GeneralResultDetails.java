package com.hariti.asmaa.FranceTour.Entities.Embeddebales;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class GeneralResultDetails {
    private LocalTime totalTime;
    private int finalRank;

    public LocalTime getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(LocalTime totalTime) {
        this.totalTime = totalTime;
    }

    public int getFinalRank() {
        return finalRank;
    }

    public void setFinalRank(int finalRank) {
        this.finalRank = finalRank;
    }
}