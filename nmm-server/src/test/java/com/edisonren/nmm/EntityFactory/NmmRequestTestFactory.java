package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.NmmRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

/**
 * Created by edison on 9/3/17.
 */
public class NmmRequestTestFactory {
    private static final String RAW_JSON = "{\"a\": \"yoyoyoyo\"}";

    private static Gson parser = new Gson();

    public static NmmRequest create() {
        NmmRequest res = new NmmRequest();
        res.setScenario(ScenarioTestFactory.create());
        res.setResponse(parser.fromJson(RAW_JSON, JsonNode.class));
        return res;
    }
}
