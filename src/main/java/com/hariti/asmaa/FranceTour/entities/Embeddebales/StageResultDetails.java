package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import javax.persistence.Embeddable;
import java.time.Duration;
import java.time.LocalTime;

@Embeddable
public class StageResultDetails {
    private LocalTime startTime;
    private Duration duration;

}