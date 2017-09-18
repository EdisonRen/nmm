package com.edisonren.nmm.service;

import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmRequest;

import java.util.List;

/**
 * Created by edison on 9/3/17.
 */
public interface NmmService {
    public NmmModel processNmmRequest(NmmRequest nmmRequest);

    public List<NmmModel> getNmmModelByServiceName(String ServiceName);
    public NmmModel getNmmModel(String serviceName, String scenarioId);

    public Long deleteNmmModelByScenarioId(String serviceName, String scenarioId);
}
