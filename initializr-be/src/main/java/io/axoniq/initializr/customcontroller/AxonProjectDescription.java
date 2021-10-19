package io.axoniq.initializr.customcontroller;

import io.spring.initializr.generator.project.MutableProjectDescription;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * A custom {@link ProjectDescription} to convey the additional fields to {@link ProjectContributor}.

 * @author Stefan Dragisic
 */
public class AxonProjectDescription extends MutableProjectDescription {

    private String axonServerContext;

    private String usingAxonServer;

    AxonProjectDescription() {
    }

    AxonProjectDescription(AxonProjectDescription source) {
        super(source);
        this.axonServerContext = source.getAxonServerContext();
        this.usingAxonServer = source.getUsingAxonServer();
    }

    @Override
    public AxonProjectDescription createCopy() {
        return new AxonProjectDescription(this);
    }

    /**
     * @return a context name specified by user.
     * Default context is "default"
     */
    public String getAxonServerContext() {
        return this.axonServerContext;
    }

    void setAxonServerContext(String axonServerContext) {
        this.axonServerContext = axonServerContext;
    }


    /**
     * @return which Axon Server version to include in resources.
     * Options: EE,SE,CLOUD
     */
    public String getUsingAxonServer() {
        return usingAxonServer;
    }

    public void setUsingAxonServer(String usingAxonServer) {
        this.usingAxonServer = usingAxonServer;
    }
}
