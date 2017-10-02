package com.edisonren.nmm.v1;

import com.edisonren.nmm.utils.Utils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by edison on 9/2/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class ScenarioInfo implements Serializable {
    private static final long serialVersionUID = 4L;

    private String scenarioId; // UUID: S-xxx
    private Date createdDate;
    private Date updatedDate;

    public ScenarioInfo() {
        this.scenarioId = Utils.generateUUID(Scenario.PREFIX);
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
