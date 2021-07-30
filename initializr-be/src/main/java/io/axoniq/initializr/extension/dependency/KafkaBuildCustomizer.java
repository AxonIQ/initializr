package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `kafka-clients` if `axon-kafka-starter`
 * is selected.
 *
 * @author Иван Дугалић
 */
public class KafkaBuildCustomizer implements BuildCustomizer<Build> {

    public static final String KAFKA_STARTER = "axon-kafka-starter";
    public static final String KAFKA_CLIENTS = "kafka-clients";

    @Override
    public void customize(Build build) {
        DependencyContainer dependencies = build.dependencies();
        if (dependencies.has(KAFKA_STARTER) && !dependencies.has(KAFKA_CLIENTS)) {
            dependencies.add(KAFKA_CLIENTS);
        }
    }
}
