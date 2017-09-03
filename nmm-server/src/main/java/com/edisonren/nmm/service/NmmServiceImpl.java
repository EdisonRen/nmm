package com.edisonren.nmm.service;

import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.NmmResponse;
import com.edisonren.nmm.v1.Scenario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edison on 9/3/17.
 */
public class NmmServiceImpl implements NmmService {

    public NmmResponse processNmmRequest(NmmRequest nmmRequest) {
        return new NmmResponse();
    }

    public List<NmmResponse> getResponsesByScenario(Scenario scenario) {
        return new ArrayList<>();
    }
    public NmmResponse getResponseByScenarioId(String scenarioId) {
        return new NmmResponse();
    }
}
