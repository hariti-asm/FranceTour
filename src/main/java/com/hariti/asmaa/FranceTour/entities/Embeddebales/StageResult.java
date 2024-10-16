package com.hariti.asmaa.FranceTour.entities.Embeddebales;

import com.hariti.asmaa.FranceTour.entities.Cyclist;
import com.hariti.asmaa.FranceTour.entities.Stage;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.*;

@Entity
public class StageResult {
    @EmbeddedId
    private StageResultId id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id")
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("stageId")
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @Embedded
    private StageResultDetails details;

}
