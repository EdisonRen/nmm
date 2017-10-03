package com.edisonren.nmm.rest;

import com.edisonren.nmm.service.NmmService;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
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
     * Save the association of scenario and static response, and publish it to message bus
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public NmmModel processNmmRequest(@RequestBody NmmRequest request) {
        logger.info("process NmmRequest");
        return nmmService.processNmmRequest(request);
    }

    /**
     * <p>Get specific NmmModel for given serviceName and scenarioId.
     * <p>Get all NmmModel associated with the ServiceName if scenario is absent.
     *
     * @param serviceName
     * @param scenarioId
     * @return List of NmmModel or 404.
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<NmmModel> getNmmModelByServiceNameAndScenarioId(
            @RequestParam("serviceName") String serviceName,
            @RequestParam(name="scenarioId", required = false) String scenarioId) {
        if (StringUtils.isEmpty(serviceName)) {
            throw new IllegalArgumentException("ServiceName is required.");
        } else if (StringUtils.isEmpty(scenarioId)) {
            logger.info("Get NmmModels by serviceName {}.", serviceName);
            return nmmService.getNmmModelByServiceName(serviceName);
        } else {
            logger.info("Get NmmModel by {},{}.", serviceName, scenarioId);
            return Collections.singletonList(nmmService.getNmmModel(serviceName, scenarioId));
        }
    }

    /**
     * Detele by ServiceName and SenarioId.
     *
     * @param serviceName
     * @param scenarioId
     * @return Number of removed NmmModel
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Long deleteNmmModelByServiceNameAndScenarioId(
            @RequestParam("serviceName") String serviceName,
            @RequestParam("scenarioId") String scenarioId) {
        logger.info("Delete NmmModel by {}:{}", serviceName, scenarioId);
        return nmmService.deleteNmmModelByScenarioId(serviceName, scenarioId);
    }

    /**
     * Detele by ServiceName and SenarioId.
     *
     * @param serviceName
     * @return the NmmModel deleted
     */
    @RequestMapping(value = "/purgeService", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<NmmModel> deleteNmmResponseByServiceName(
            @RequestParam("serviceName") String serviceName) {
        logger.info("Purge NmmModels by serviceName:{}", serviceName);
        List<NmmModel> models = nmmService.getNmmModelByServiceName(serviceName);
        models.stream()
                .map(model -> model.getScenarioInfo().getScenarioId())
                .forEach(id -> nmmService.deleteNmmModelByScenarioId(serviceName, id));

        return models;
    }


    // TODO: add integration tests, of course
    // TODO: @ExceptionHandler
}
