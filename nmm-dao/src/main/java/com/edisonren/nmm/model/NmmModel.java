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

    public static class NmmModelBuilder {
        private Scenario scenario;
        private ScenarioInfo scenarioInfo;
        private Integer mockVer;
        private JsonNode response;

        public NmmModelBuilder() {}

        public NmmModelBuilder setScenario(Scenario scenario) {
            this.scenario = scenario;
            return this;
        }

        public NmmModelBuilder setScenarioInfo(ScenarioInfo scenarioInfo) {
            this.scenarioInfo = scenarioInfo;
            return this;
        }

        public NmmModelBuilder setMockVer(Integer mockVer) {
            this.mockVer = mockVer;
            return this;
        }

        public NmmModelBuilder setResponse(JsonNode response) {
            this.response = response;
            return this;
        }

        public NmmModel build() {
            assertField(scenario, "Scenario");
            assertField(scenarioInfo, "ScenarioInfo");
            assertField(mockVer, "MockVer");
            assertField(response, "Response");

            return new NmmModel(this);
        }

        private void assertField(Object field, String name) {
            if (field == null) {
                throw new IllegalArgumentException(String.format("%s is required by NmmModel.", name));
            }
        }
    }

    public NmmModel() {}

    public NmmModel(NmmModelBuilder builder) {
        this.scenario = builder.scenario;
        this.scenarioInfo = builder.scenarioInfo;
        this.mockVer = builder.mockVer;
        this.response = builder.response;
    }

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
