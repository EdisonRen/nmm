package com.edisonren.nmm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by edison on 8/26/17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired private MockMvc mvc;

    //@Test
    public void getDoshit() throws Exception {
        mvc
                .perform(MockMvcRequestBuilders.get("/health-check")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
