package com.edisonren.nmm.v1;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * The NMM request POST by clients, in which a <code>scenario</code> is defined with the <code>response</code>
 * supposed to be returned.
 * <p>
 * A new field isMockedResponse is set to true in JsonNode <code>response</code>.
 * <p>
 * Created by edison on 8/27/17.
 */
public class NmmRequest {

    private Scenario scenario;
    private JsonNode response;

    public NmmRequest(Scenario scenario, JsonNode response) {
        this.scenario = scenario;
        this.response = response;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public JsonNode getResponse() {
        return response;
    }

    public void setResponse(JsonNode response) {
        this.response = response;
    }
}
