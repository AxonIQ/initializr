package io.axoniq.initializr.metrics;

import io.spring.initializr.actuate.stat.ProjectRequestDocument;

/**
 * Represent the request for Axon project generation.
 *
 * @author Ivan Dugalic
 */
public class AxonProjectRequestDocument extends ProjectRequestDocument {

    private String axonVersion;

    public String getAxonVersion() {
        return axonVersion;
    }

    public void setAxonVersion(String axonVersion) {
        this.axonVersion = axonVersion;
    }
}
