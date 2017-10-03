package com.edisonren.nmm.v1;

import com.edisonren.nmm.utils.LocalDateDeserializer;
import com.edisonren.nmm.utils.LocalDateSerializer;
import com.edisonren.nmm.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by edison on 9/2/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ScenarioInfo implements Serializable {
    private static final long serialVersionUID = 4L;

    private String scenarioId; // UUID: S-xxx

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate createdDate;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate updatedDate;

    public ScenarioInfo() {
        this.scenarioId = Utils.generateUUID(Scenario.PREFIX);
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}
