package com.edisonren.nmm.validator;

import com.edisonren.nmm.v1.NmmRequest;

/**
 * Created by edison on 9/3/17.
 */
public class NmmRequestValidator {

    public static void validate(NmmRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }

        if (request.getResponse() == null) {
            throw new IllegalArgumentException("Define the expected Response in NmmRequest.");
        }

        ScenarioValidator.validate(request.getScenario());
    }
}
