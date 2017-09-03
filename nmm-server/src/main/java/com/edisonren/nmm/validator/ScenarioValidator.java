package com.edisonren.nmm.validator;

import com.edisonren.nmm.v1.Scenario;
import org.springframework.util.StringUtils;

/**
 * Created by edison on 9/3/17.
 */
public class ScenarioValidator {

    public static void validate(Scenario scenario) {
        if (scenario == null) {
            throw new IllegalArgumentException("The scenario is missing.");
        }

        if (StringUtils.isEmpty(scenario.getServiceName())) {
            throw new IllegalArgumentException("Service Name is necessary in a valid scenario.");
        }
    }
}
