package com.edisonren.nmm.EntityFactory;

import com.edisonren.nmm.v1.ScenarioInfo;

import java.util.Date;

/**
 * Created by edison on 9/3/17.
 */
public class ScenarioInfoTestFactory {
    private static final String ID = "S-123";

    public static ScenarioInfo create() throws Exception {
        ScenarioInfo res = new ScenarioInfo();
        res.setCreatedDate(new Date());
        res.setScenarioId(ID);
        return res;
    }
}
