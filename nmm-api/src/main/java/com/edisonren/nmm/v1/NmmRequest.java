package com.edisonren.nmm.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * The NMM request POST by clients, in which a <code>scenario</code> is defined with the <code>response</code>
 * supposed to be returned.
 * <p>
 * A new field isMockedResponse is set to true in JsonNode <code>response</code>.
 * <p>
 * Created by edison on 8/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class NmmRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Scenario scenario;
    private JsonNode response;
    private Integer priority;

    public NmmRequest() {}

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
