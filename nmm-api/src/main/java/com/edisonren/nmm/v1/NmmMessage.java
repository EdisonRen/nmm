package com.edisonren.nmm.v1;

import javax.annotation.Nonnull;

/**
 * <p>The message sent to Nmm-Bus by Nmm-Server.
 * <p>The listener on Nmm-Client side subscribing the topic(ServiceName) would receive it,
 * and act according to the NmmOperation
 *
 * <p>Created by edison on 10/6/17.
 */
public class NmmMessage {
    @Nonnull private NmmOperation operation;
    @Nonnull private String serviceName;
    private NmmModel model;

    public NmmMessage(NmmOperation operation, NmmModel model) {
        this.operation = operation;
        this.model = model;
    }

    @Nonnull
    public NmmOperation getOperation() {
        return operation;
    }
    
    public void setOperation(NmmOperation operation) {
        this.operation = operation;
    }

    public NmmModel getModel() {
        return model;
    }

    public void setModel(NmmModel model) {
        this.model = model;
    }

    @Nonnull
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(@Nonnull String serviceName) {
        this.serviceName = serviceName;
    }
}
