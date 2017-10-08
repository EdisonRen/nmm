package com.edisonren.nmm.service;

import com.edisonren.nmm.kafka.Sender;
import com.edisonren.nmm.dao.NmmModelRepository;
import com.edisonren.nmm.v1.NmmMessage;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmOperation;
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

    @Autowired
    private Sender sender;

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

        sender.send(new NmmMessage(NmmOperation.CREATE, model));

        return model;
    }

    @Override
    public List<NmmModel> getNmmModelByServiceName(String serviceName) {
        NmmRequestValidator.validateServiceName(serviceName);

        return new ArrayList<>(
                modelRepository.findNmmModelsByServiceName(serviceName).values());
    }

    @Override
    public NmmModel getNmmModel(String serviceName, String scenarioId) {
        NmmRequestValidator.validateServiceName(serviceName);
        NmmRequestValidator.validateScenarioId(scenarioId);

        return modelRepository.getNmmModel(serviceName, scenarioId);
    }

    @Override
    public Long deleteNmmModelByScenarioId(String serviceName, String scenarioId) {
        NmmRequestValidator.validateServiceName(serviceName);
        NmmRequestValidator.validateScenarioId(scenarioId);

        // TODO: publish DELETE NmmMessage

        return modelRepository.deleteNmmModel(serviceName, scenarioId);
    }
}
