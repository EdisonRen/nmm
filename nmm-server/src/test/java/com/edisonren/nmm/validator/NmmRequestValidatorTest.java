package com.edisonren.nmm.validator;

import com.edisonren.nmm.EntityFactory.NmmRequestTestFactory;
import com.edisonren.nmm.v1.NmmRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * I cannot believe I become a dev willing to write tests, even without any pressure from code coverage tools
 *
 * Created by edison on 9/3/17.
 */
public class NmmRequestValidatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void missingResponseTest() throws Exception {
        NmmRequest request = NmmRequestTestFactory.create();
        request.setResponse(null);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Define the expected Response in NmmRequest.");

        NmmRequestValidator.validate(request);
    }

    @Test
    public void missingRequestTest() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Request cannot be null.");

        NmmRequestValidator.validate(null);
    }

    @Test
    public void missingScenarioTest() throws Exception {
        NmmRequest request = NmmRequestTestFactory.create();
        request.setScenario(null);

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The scenario is missing.");

        NmmRequestValidator.validate(request);
    }

    @Test
    public void missingServiceNameTest() throws Exception {
        NmmRequest request = NmmRequestTestFactory.create();
        request.getScenario().setServiceName("");

        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Service Name is necessary in a valid scenario.");

        NmmRequestValidator.validate(request);
    }
}
