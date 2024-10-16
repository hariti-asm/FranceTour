package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class GeneralResultDetails {
    private LocalTime totalTime;
    private int finalRank;

    // Constructors
    public GeneralResultDetails() {}

    public GeneralResultDetails(LocalTime totalTime, int finalRank) {
        this.totalTime = totalTime;
        this.finalRank = finalRank;
    }

    // Getters and Setters
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

    // You might want to add equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralResultDetails that = (GeneralResultDetails) o;
        return finalRank == that.finalRank &&
                java.util.Objects.equals(totalTime, that.totalTime);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(totalTime, finalRank);
    }

    @Override
    public String toString() {
        return "GeneralResultDetails{" +
                "totalTime=" + totalTime +
                ", finalRank=" + finalRank +
                '}';
    }
}