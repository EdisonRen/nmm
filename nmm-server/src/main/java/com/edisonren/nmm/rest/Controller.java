package com.edisonren.nmm.rest;

import com.edisonren.nmm.service.NmmService;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmRequest;
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
import java.util.Collections;
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

    // TODO: update

    /**
     * Save the association of scenario and static response
     * , and publish it to message bus
     */
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NmmModel processNmmRequest(@RequestBody NmmRequest request) {
        logger.info("process NmmRequest");
        return nmmService.processNmmRequest(request);
    }

//    @RequestMapping(value = "/{serviceName}/{scenarioId}", method = RequestMethod.GET,
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    public List<NmmModel> getNmmResponseByServiceNameScenarioId(
//            @PathVariable("serviceName") String serviceName,
//            @PathVariable("scenarioId") String scenarioId) {
//        if (scenarioId == null) {
//            logger.info("Get NmmModels by serviceName {}.", serviceName);
//            return nmmService.getNmmModelByServiceName(serviceName);
//        } else {
//            logger.info("Get NmmModel by {},{}.", serviceName, scenarioId);
//            return Collections.singletonList(nmmService.getNmmModel(serviceName, scenarioId));
//        }
//    }

    // TODO: @ExceptionHandler
}
