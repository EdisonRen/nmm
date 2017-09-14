package com.edisonren.nmm.model;

import com.edisonren.nmm.v1.Scenario;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Created by edison on 9/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class NmmModel  implements Serializable {
    private static final long serialVersionUID = 22L;

    private Scenario scenario;
    private ScenarioInfo scenarioInfo;
    private Integer mockVer;
    private JsonNode response;

    // TODO: builder

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public ScenarioInfo getScenarioInfo() {
        return scenarioInfo;
    }

    public void setScenarioInfo(ScenarioInfo scenarioInfo) {
        this.scenarioInfo = scenarioInfo;
    }

    public Integer getMockVer() {
        return mockVer;
    }

    public void setMockVer(Integer mockVer) {
        this.mockVer = mockVer;
    }

    public JsonNode getResponse() {
        return response;
    }

    public void setResponse(JsonNode response) {
        this.response = response;
    }
}
