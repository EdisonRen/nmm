package com.edisonren.nmm.rest;

import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.NmmResponse;
import com.edisonren.nmm.v1.Scenario;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by edison on 8/26/17.
 */
@RestController
@RequestMapping(value = "/nmm/v1")
public class Controller {

    @RequestMapping(value = "/health-check",
            produces = {MediaType.TEXT_PLAIN_VALUE},
            method = RequestMethod.GET)
    public String doshit() {
        return "This shit works"; // TODO: use something more informative, or at least appropriate
    }

    @ResponseBody
    @RequestMapping(value = "/currentVersion",
            produces = {MediaType.TEXT_PLAIN_VALUE},
            method = RequestMethod.GET)
    public String getVersion() {
        return "1.00"; // TODO: worry about this later

    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NmmResponse postNmmRequest(@RequestBody NmmRequest request) {
        return null;
    }

    /**
     *
     * @param senario only
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/view", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NmmResponse matchNmmResponse(@RequestBody Scenario senario) {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/{scenarioId}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public NmmResponse getNmmResponseByLoanId(@PathVariable("scenarioId") String scenarioId) {
        return null;
    }
}
