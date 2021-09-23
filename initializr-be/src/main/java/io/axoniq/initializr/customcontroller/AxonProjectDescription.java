package io.axoniq.initializr.customcontroller;

import io.spring.initializr.generator.project.MutableProjectDescription;

/**
 * A custom ProjectDescription to convey the additional flags to contributors like context.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectDescription extends MutableProjectDescription {

    private String axonServerContext;

    AxonProjectDescription() {
    }

    AxonProjectDescription(AxonProjectDescription source) {
        super(source);
        this.axonServerContext = source.getAxonServerContext();
    }

    @Override
    public AxonProjectDescription createCopy() {
        return new AxonProjectDescription(this);
    }

    public String getAxonServerContext() {
        return this.axonServerContext;
    }

    void setAxonServerContext(String axonServerContext) {
        this.axonServerContext = axonServerContext;
    }

}
