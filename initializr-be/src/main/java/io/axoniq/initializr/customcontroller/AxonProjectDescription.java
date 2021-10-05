package io.axoniq.initializr.customcontroller;

import io.spring.initializr.generator.project.MutableProjectDescription;

/**
 * A custom ProjectDescription to convey the additional flags to contributors like context.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectDescription extends MutableProjectDescription {

    private String axonServerContext;

    private boolean usingAxonSaaS;

    private boolean usingAxonServerSE;

    private boolean usingAxonServerEE;

    AxonProjectDescription() {
    }

    AxonProjectDescription(AxonProjectDescription source) {
        super(source);
        this.axonServerContext = source.getAxonServerContext();
        this.usingAxonServerEE = source.isUsingAxonServerEE();
        this.usingAxonSaaS = source.isUsingAxonSaaS();
        this.usingAxonServerSE = source.isUsingAxonServerSE();
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
