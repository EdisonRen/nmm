package com.edisonren.nmm.service;

import com.edisonren.nmm.dao.NmmModelRepository;
import com.edisonren.nmm.model.NmmModel;
import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.NmmResponse;
import com.edisonren.nmm.v1.Scenario;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.edisonren.nmm.validator.NmmRequestValidator;
import com.edisonren.nmm.validator.ScenarioValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by edison on 9/3/17.
 */
public class NmmServiceImpl implements NmmService {

    @Autowired
    private NmmModelRepository modelRepository;

    @Override
    public NmmResponse processNmmRequest(NmmRequest nmmRequest) {
        NmmRequestValidator.validate(nmmRequest);

        NmmModel model = new NmmModel.NmmModelBuilder()
                .setMockVer(0)
                .setScenario(nmmRequest.getScenario())
                .setScenarioInfo(new ScenarioInfo())    // TODO: this is a dummy one!
                .setResponse(nmmRequest.getResponse())
                .build();

        modelRepository.saveNmmModel(model);

        return new NmmResponse();
    }

    @Override
    public List<NmmResponse> getResponsesByScenario(Scenario scenario) {
        ScenarioValidator.validate(scenario);

        return new ArrayList<>();
    }

    @Override
    public NmmResponse getResponseByScenarioId(String scenarioId) {


        return new NmmResponse();
    }

    @Override
    public List<NmmResponse> deleteResponsesByScenario(Scenario scenario) {

        return new ArrayList<>();
    }

    @Override
    public NmmResponse deleteResponseByScenarioId(String scenarioId) {

        return new NmmResponse();
    }
}
