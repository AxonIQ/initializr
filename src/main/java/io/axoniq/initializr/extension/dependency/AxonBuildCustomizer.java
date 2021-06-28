package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.spring.build.BuildCustomizer;


/**
 * A build customizer for Axon projects that provides explicit selection of the `axon-starter` and `axon-test` dependency.
 *
 * @author Иван Дугалић
 */
public class AxonBuildCustomizer implements BuildCustomizer<Build> {

    public static final String AXON_STARTER = "axon-starter";
    public static final String AXON_TEST = "axon-test";

    @Override
    public void customize(Build build) {

        if (!build.dependencies().has(AXON_STARTER)) {
            build.dependencies().add(AXON_STARTER);
        }

        if (!build.dependencies().has(AXON_TEST)) {
            build.dependencies().add(AXON_TEST);
        }
    }
}
