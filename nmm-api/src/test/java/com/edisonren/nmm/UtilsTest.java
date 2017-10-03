package com.edisonren.nmm;

import com.edisonren.nmm.utils.Utils;
import com.edisonren.nmm.v1.ScenarioInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by edison on 10/1/17.
 */
public class UtilsTest {

    @Test
    public void UUIDTest() {
        assertTrue(Utils.generateUUID("T").length() == Utils.UUID_LEN + 2);
    }
}
