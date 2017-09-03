package com.edisonren.nmm.v1;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Scenario defines the situation that a mocked response is returned.
 * <p>
 * Scenario can be determined by payload, remoteAddress, remoteHost and remotePort.
 * <p>
 * Each Non-null property is used to evaluate the <code>scenario</code>
 * <p>
 * Created by edison on 8/27/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class Scenario implements Serializable {
    private static final long serialVersionUID = 3L;

    // ----------------------------------------------------------------
    // This field is required
    // ----------------------------------------------------------------
    // Customized name of the Service which integrates the Nmm Client.
    // The service can be a controller or a simply a interface.
    // A topic in message bus is created after this name, if not existed.
    // Any new Scenario matching this name is published to such topic
    // , and the client attached to the such service subscribe the topic
    // , for any update or creation
    @Nonnull private String serviceName;

    // ------------- the two fields below are identifiers -------------
    // the clients use the identifiers to match the source incoming requests
    // If not given, any traffic intercepted by the client is parsed to match the payload
    // , which is quite an expensive operation.
    // ----------------------------------------------------------------
    // IP address of the client or last proxy that sent the request
    @Nullable private String remoteAddr;
    // fully qualified name of the client or the last proxy that sent the request
    @Nullable private String remoteHost;

    // ------------- the two fields below are fine tuners -------------
    // If provided, these two fields are used to match the scenario with the request.
    // You can imagine, if identifiers and tuners are missing
    // , I would rather call the scenario a Hodor case
    // ----------------------------------------------------------------
    // request body
    @Nullable private JsonNode payload;
    // parameter values
    @Nullable private Set<String> parameters;

    public Scenario() {}

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(serviceName)
                .append(remoteAddr)
                .append(remoteHost)
                .append(payload)
                .append(parameters)
                .toHashCode();
    }

    // The sequence of parameters matters, or this function would be too expensive.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scenario)) return false;

        Scenario scenario = (Scenario) o;

        if (!getServiceName().equals(scenario.getServiceName())) return false;
        if (getRemoteAddr() != null ? !getRemoteAddr().equals(scenario.getRemoteAddr()) : scenario.getRemoteAddr() != null)
            return false;
        if (getRemoteHost() != null ? !getRemoteHost().equals(scenario.getRemoteHost()) : scenario.getRemoteHost() != null)
            return false;
        if (getPayload() != null ? !getPayload().equals(scenario.getPayload()) : scenario.getPayload() != null)
            return false;
        return getParameters() != null ? getParameters().equals(scenario.getParameters()) : scenario.getParameters() == null;
    }

    // ---- nothing interesting below ----
    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    @Nullable
    public Set<String> getParameters() {
        if (parameters == null) parameters = new HashSet<>();
        return parameters;
    }

    public void setParameters(@Nullable Set<String> parameters) {
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
