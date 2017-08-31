package com.edisonren.nmm.v1;


import com.fasterxml.jackson.databind.JsonNode;

import javax.servlet.ServletRequest;

/**
 * Scenario defines the situation that a mocked response is returned.
 * <p>
 * Scenario can be determined by payload, remoteAddress, remoteHost and remotePort.
 * <p>
 * Eech Non-null property is used to evaluate the <code>scenario</code>
 * <p>
 * Created by edison on 8/27/17.
 */
public class Scenario {

    // request body
    private JsonNode payload;
    // parameter values
    private String[] parameters;
    // IP address of the client or last proxy that sent the request
    private String remoteAddr;
    // fully qualified name of the client or the last proxy that sent the request
    private String remoteHost;
    // IP source port of the client or last proxy that sent the request
    private String remotePort;

    public Scenario(ServletRequest request) {
        // TODO
    }

    public Boolean isMatched(ServletRequest request) {
        // TODO
        return true;
    }

    @Override
    public int hashCode() {
        // TODO: hashCodeBuilder
        return 1;
    }

    // ---- nothing interesting below ----
    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(String remotePort) {
        this.remotePort = remotePort;
    }
}
