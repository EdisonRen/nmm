package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.NmmResponse;

/**
 * Created by edison on 9/3/17.
 */
public class NmmResponseTestFactory {

    public static NmmResponse create() {
        NmmResponse res = new NmmResponse();
        res.setMockVer(0);
        res.setScenario(ScenarioTestFactory.create());
        res.setScenarioInfo(ScenarioInfoTestFactory.create());
        return res;
    }
}
