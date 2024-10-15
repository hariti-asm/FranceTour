package com.hariti.asmaa.FranceTour.Entities.Embeddebales;

import com.hariti.asmaa.FranceTour.Entities.Cyclist;
import com.hariti.asmaa.FranceTour.Entities.Stage;

import javax.persistence.EmbeddedId;
import javax.persistence.*;

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
