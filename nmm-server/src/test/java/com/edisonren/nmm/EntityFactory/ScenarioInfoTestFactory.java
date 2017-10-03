package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.ScenarioInfo;

/**
 * Created by edison on 9/3/17.
 */
public class ScenarioInfoTestFactory {
    public static final String ID = "S-123";

    public static ScenarioInfo create() throws Exception {
        ScenarioInfo res = new ScenarioInfo();
        res.setScenarioId(ID);
        return res;
    }
}
