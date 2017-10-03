package com.edisonren.nmm.v1;

import com.edisonren.nmm.utils.LocalDateTimeDeserializer;
import com.edisonren.nmm.utils.LocalDateTimeSerializer;
import com.edisonren.nmm.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by edison on 9/2/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ScenarioInfo implements Serializable {
    private static final long serialVersionUID = 4L;

    private String scenarioId; // UUID: S-xxx

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDate;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;

    public ScenarioInfo() {
        this.scenarioId = Utils.generateUUID(Scenario.PREFIX);
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
