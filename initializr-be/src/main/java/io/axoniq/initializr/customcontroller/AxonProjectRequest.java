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

    private boolean usingAxonSaaS;

    private boolean usingAxonServerSE;

    private boolean usingAxonServerEE;

    private String axonServerContext = "default";

    public String getAxonServerContext() {
        return this.axonServerContext;
    }

    public void setAxonServerContext(String axonServerContext) {
        this.axonServerContext = axonServerContext;
    }

    public boolean isUsingAxonSaaS() {
        return usingAxonSaaS;
    }

    public void setUsingAxonSaaS(boolean usingAxonSaaS) {
        this.usingAxonSaaS = usingAxonSaaS;
    }

    public boolean isUsingAxonServerSE() {
        return usingAxonServerSE;
    }

    public void setUsingAxonServerSE(boolean usingAxonServerSE) {
        this.usingAxonServerSE = usingAxonServerSE;
    }

    public boolean isUsingAxonServerEE() {
        return usingAxonServerEE;
    }

    public void setUsingAxonServerEE(boolean usingAxonServerEE) {
        this.usingAxonServerEE = usingAxonServerEE;
    }
}
