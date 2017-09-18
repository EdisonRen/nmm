package com.edisonren.nmm.service;

import com.edisonren.nmm.dao.NmmModelRepository;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.edisonren.nmm.validator.NmmRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edison on 9/3/17.
 */
public class NmmServiceImpl implements NmmService {

    @Autowired
    private NmmModelRepository modelRepository;

    @Override
    public NmmModel processNmmRequest(NmmRequest nmmRequest) {
        NmmRequestValidator.validate(nmmRequest);

        NmmModel model = new NmmModel.NmmModelBuilder()
                .setMockVer(0)
                .setScenario(nmmRequest.getScenario())
                .setScenarioInfo(new ScenarioInfo())
                .setResponse(nmmRequest.getResponse())
                .build();

        modelRepository.saveNmmModel(model);
        // TODO: publish to message bus

        return model;
    }

    @Override
    public List<NmmModel> getNmmModelByServiceName(String serviceName) {
        return new ArrayList<>(
                modelRepository.findNmmModelsByServiceName(serviceName).values());
    }

    @Override
    public NmmModel getNmmModel(String serviceName, String scenarioId) {
        return modelRepository.getNmmModel(serviceName, scenarioId);
    }

    @Override
    public Long deleteNmmModelByScenarioId(String serviceName, String scenarioId) {
        return modelRepository.deleteNmmModel(serviceName, scenarioId);
    }
}
