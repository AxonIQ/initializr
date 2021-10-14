package io.axoniq.initializr.customcontroller;

import io.spring.initializr.web.project.ProjectRequest;
import io.spring.initializr.web.project.WebProjectRequest;

/**
 * A custom {@link ProjectRequest} with an additional custom boolean flag. This type has
 * to be public for the {@code axonServerContext} request attribute to be mapped properly.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectRequest extends WebProjectRequest {

    private String usingAxonServer = "none";

    private String axonServerContext = "default";

    /**
     * @return a context name specified by user.
     * Default context is "default"
     */
    public String getAxonServerContext() {
        return this.axonServerContext;
    }

    public void setAxonServerContext(String axonServerContext) {
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
