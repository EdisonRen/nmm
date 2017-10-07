package com.edisonren.nmm;

import com.edisonren.nmm.v1.NmmMessage;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.Scenario;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by edison on 10/6/17.
 */
public class NmmMessageTest {
    @Rule public final ExpectedException exception = ExpectedException.none();


    @Test
    public void testNmmMessageCreationWithNullModel() {
        exception.expect(IllegalArgumentException.class);

        new NmmMessage(null, null);
    }

    @Test
    public void testNmmMessageCreationWithNullScenario() {
        exception.expect(IllegalArgumentException.class);

        NmmModel model = new NmmModel();
        model.setScenario(null);

        new NmmMessage(null, model);
    }

    @Test
    public void testNmmMessageCreationWithEmptyServiceName() {
        exception.expect(IllegalArgumentException.class);

        NmmModel model = new NmmModel();
        Scenario scenario = new Scenario();
        scenario.setServiceName("");
        model.setScenario(scenario);

        new NmmMessage(null, model);
    }

    @Test
    public void happyPath() {

        NmmModel model = new NmmModel();
        Scenario scenario = new Scenario();
        String serviceName = "shit";
        scenario.setServiceName(serviceName);
        model.setScenario(scenario);

        NmmMessage msg = new NmmMessage(null, model);
        Assert.assertEquals(serviceName, msg.getServiceName());
    }
}
