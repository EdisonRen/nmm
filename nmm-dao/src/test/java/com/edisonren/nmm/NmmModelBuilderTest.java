package com.edisonren.nmm;

import com.edisonren.nmm.model.NmmModel;
import com.edisonren.nmm.v1.Scenario;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by edison on 9/16/17.
 */
public class NmmModelBuilderTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private static ObjectMapper mapper = new ObjectMapper();

    private static String serviceName = " (-__-) ";
    private static String scenarioId = "v2";
    private static Integer mockVer = 2;
    private static String response = "{\"a\" : 1}";

    private Scenario scenario = new Scenario();
    private ScenarioInfo info = new ScenarioInfo();

    @Test
    public void happyPath() throws Exception {
        scenario.setServiceName(serviceName);
        info.setScenarioId(scenarioId);

        NmmModel model = new NmmModel.NmmModelBuilder()
                .setScenario(scenario)
                .setScenarioInfo(info)
                .setMockVer(mockVer)
                .setResponse(mapper.readValue(response, JsonNode.class))
                .build();

        assertEquals(serviceName, model.getScenario().getServiceName());
        assertEquals(scenarioId, model.getScenarioInfo().getScenarioId());
        assertEquals(mockVer, model.getMockVer());
        assertEquals(1, model.getResponse().get("a").intValue());
    }

    @Test
    public void missingScenarioTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Scenario is required by NmmModel.");

        new NmmModel.NmmModelBuilder()
                .setScenarioInfo(info)
                .setMockVer(mockVer)
                .setResponse(mapper.readValue(response, JsonNode.class))
                .build();
    }

    @Test
    public void missingResponseTest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Response is required by NmmModel.");

        new NmmModel.NmmModelBuilder()
                .setScenario(scenario)
                .setScenarioInfo(info)
                .setMockVer(mockVer)
                .build();
    }
}
