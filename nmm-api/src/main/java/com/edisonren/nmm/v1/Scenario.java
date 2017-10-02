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
    public static final String PREFIX = "S";

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
    // IP v4 addresses of the client or last proxy that sent the request
    @Nullable private Set<String> includeRemoteAddr;
    @Nullable private Set<String> excludeRemoteAddr;

    // fully qualified name of the client or the last proxy that sent the request
    @Nullable private Set<String> includeRemoteHost;
    @Nullable private Set<String> excludeRemoteHost;

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
                .append(includeRemoteAddr)
                .append(excludeRemoteAddr)
                .append(includeRemoteHost)
                .append(excludeRemoteHost)
                .append(payload)
                .append(parameters)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scenario)) return false;

        Scenario scenario = (Scenario) o;

        if (!getServiceName().equals(scenario.getServiceName())) return false;
        if (getIncludeRemoteAddr() != null ?
                !getIncludeRemoteAddr().equals(scenario.getIncludeRemoteAddr()) : scenario.getIncludeRemoteAddr() != null)
            return false;
        if (getExcludeRemoteAddr() != null ?
                !getExcludeRemoteAddr().equals(scenario.getExcludeRemoteAddr()) : scenario.getExcludeRemoteAddr() != null)
            return false;
        if (getIncludeRemoteHost() != null ?
                !getIncludeRemoteHost().equals(scenario.getIncludeRemoteHost()) : scenario.getIncludeRemoteHost() != null)
            return false;
        if (getExcludeRemoteHost() != null ?
                !getExcludeRemoteHost().equals(scenario.getExcludeRemoteHost()) : scenario.getExcludeRemoteHost() != null)
            return false;
        if (getPayload() != null ? !getPayload().equals(scenario.getPayload()) : scenario.getPayload() != null)
            return false;
        return getParameters() != null ?
                getParameters().equals(scenario.getParameters()) : scenario.getParameters() == null;
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

    @Nullable
    public Set<String> getIncludeRemoteAddr() {
        return includeRemoteAddr;
    }

    public void setIncludeRemoteAddr(@Nullable Set<String> includeRemoteAddr) {
        this.includeRemoteAddr = includeRemoteAddr;
    }

    @Nullable
    public Set<String> getExcludeRemoteAddr() {
        return excludeRemoteAddr;
    }

    public void setExcludeRemoteAddr(@Nullable Set<String> excludeRemoteAddr) {
        this.excludeRemoteAddr = excludeRemoteAddr;
    }

    @Nullable
    public Set<String> getIncludeRemoteHost() {
        return includeRemoteHost;
    }

    public void setIncludeRemoteHost(@Nullable Set<String> includeRemoteHost) {
        this.includeRemoteHost = includeRemoteHost;
    }

    @Nullable
    public Set<String> getExcludeRemoteHost() {
        return excludeRemoteHost;
    }

    public void setExcludeRemoteHost(@Nullable Set<String> excludeRemoteHost) {
        this.excludeRemoteHost = excludeRemoteHost;
    }

    public void setParameters(@Nullable Set<String> parameters) {
        this.parameters = parameters;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
