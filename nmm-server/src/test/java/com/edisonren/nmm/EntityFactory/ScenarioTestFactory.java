package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.Scenario;

/**
 * Created by edison on 9/3/17.
 */
public class ScenarioTestFactory {
    public static final String SERVICE_NAME = "TEST-SERVICE";

    public static Scenario create() {
        Scenario res = new Scenario();
        res.setServiceName(SERVICE_NAME);
        return res;
    }
}
