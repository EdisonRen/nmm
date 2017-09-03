package com.edisonren.nmm.service;

import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.NmmResponse;
import com.edisonren.nmm.v1.Scenario;

import java.util.List;

/**
 * Created by edison on 9/3/17.
 */
public interface NmmService {
    public NmmResponse processNmmRequest(NmmRequest nmmRequest);
    public List<NmmResponse> getResponsesByScenario(Scenario scenario);
    public NmmResponse getResponseByScenarioId(String scenarioId);
}
