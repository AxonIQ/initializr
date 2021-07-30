package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `spring amqp` if `axon-amqp-starter` is
 * selected.
 *
 * @author Иван Дугалић
 */
public class AMQPBuildCustomizer implements BuildCustomizer<Build> {

    public static final String AMQP_STARTER = "axon-amqp-starter";
    public static final String SPRING_AMQP_STARTER = "amqp";

    @Override
    public void customize(Build build) {
        DependencyContainer dependencies = build.dependencies();
        if (dependencies.has(AMQP_STARTER) && !dependencies.has(SPRING_AMQP_STARTER)) {
            dependencies.add(SPRING_AMQP_STARTER);
        }
    }
}
