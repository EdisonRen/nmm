package com.edisonren.nmm.model;

import com.edisonren.nmm.v1.Scenario;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by edison on 9/13/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class NmmModel  implements Serializable {
    private static final long serialVersionUID = 22L;

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ObjectWriter writer = mapper.writer();
    private static final ObjectReader reader = mapper.reader();

    private Scenario scenario;
    private ScenarioInfo scenarioInfo;
    private Integer mockVer;
    private byte[] response;

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
            assertField(scenario.getServiceName(), "Scenario.serviceName");

            assertField(scenarioInfo, "ScenarioInfo");
            assertField(scenarioInfo, "ScenarioInfo.ScenarioId");

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
        setResponse(builder.response);
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
        try {
            return reader.readTree(new ByteArrayInputStream(this.response));
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to convert byte array to JsonNode.");
        }
    }

    public void setResponse(JsonNode response) {
        try {
            this.response = writer.writeValueAsBytes(response);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Failed to parse JsonNode to byte array.");
        }
    }
}
