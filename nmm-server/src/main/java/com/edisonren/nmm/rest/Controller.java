package com.edisonren.nmm.rest;

import com.edisonren.nmm.service.NmmService;
import com.edisonren.nmm.v1.NmmRequest;
import com.edisonren.nmm.v1.NmmResponse;
import com.edisonren.nmm.v1.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by edison on 8/26/17.
 */
@RestController
@RequestMapping(value = "/nmm/v1")
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject private NmmService nmmService;

    @RequestMapping(value = "/health-check",
            produces = {MediaType.TEXT_PLAIN_VALUE},
            method = RequestMethod.GET)
    public String doshit(HttpServletRequest request) {
        logger.info("Requested by {} {}", request.getRemoteAddr(), request.getRemoteHost());

        return "This shit works"; // TODO: use something more informative, or at least appropriate
    }

    @RequestMapping(value = "/currentVersion",
            produces = {MediaType.TEXT_PLAIN_VALUE},
            method = RequestMethod.GET)
    public String getVersion() {
        return "1.00"; // TODO: worry about this later

    }

    /**
     * Save the association of scenario and static response
     * , and publish it to message bus
     */
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NmmResponse processNmmRequest(@RequestBody NmmRequest request) {
        logger.info("process NmmRequest");
        return nmmService.processNmmRequest(request);
    }

    /**
     * For given Scenario, return all matched Responses.
     *
     * @param scenario payload and parameters are not used to evaluate the scenario.
     * @return list of matched NmmResponse, whose scenario matches the given one.
     */
    @RequestMapping(value = "/match", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<NmmResponse> matchScenario(@RequestBody Scenario scenario) {
        logger.info("match scenario");
        return nmmService.getResponsesByScenario(scenario);
    }

    @RequestMapping(value = "/match/{scenarioId}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public NmmResponse getNmmResponseByLoanId(@PathVariable("scenarioId") String scenarioId) {
        logger.info("match scenario by id");
        return nmmService.getResponseByScenarioId(scenarioId);
    }

    // TODO: @ExceptionHandler
}
