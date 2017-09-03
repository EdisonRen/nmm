package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.NmmRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by edison on 9/3/17.
 */
public class NmmRequestTestFactory {
    private static final String RAW_JSON = "{\"a\": \"yoyoyoyo\"}";

    private static ObjectMapper mapper = new ObjectMapper();

    public static NmmRequest create() throws Exception {
        NmmRequest res = new NmmRequest();
        res.setScenario(ScenarioTestFactory.create());
        res.setResponse(mapper.readTree(RAW_JSON));
        return res;
    }
}
