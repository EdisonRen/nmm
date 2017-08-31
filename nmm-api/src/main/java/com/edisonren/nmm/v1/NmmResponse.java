package com.edisonren.nmm.v1;

/**
 * The NMM response from NMM Service API
 * <p>
 * scenarioId is a UUID. mockVer is the version number for the same scenario.
 * <p>
 * Created by edison on 8/27/17.
 */
public class NmmResponse {

    private Scenario scenario;
    private String scenarioId; // UUID: S-xxx
    private Integer mockVer;   // like optimistic lock

    public NmmResponse() {
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Integer getMockVer() {
        return mockVer;
    }

    public void setMockVer(Integer mockVer) {
        this.mockVer = mockVer;
    }
}
