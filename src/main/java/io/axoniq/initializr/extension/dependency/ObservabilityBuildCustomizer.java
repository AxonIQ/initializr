package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

import java.util.Arrays;
import java.util.List;

/**
 * A build customizer for Axon projects that provides explicit selection of the `actuator` dependency
 * if one of the observability related dependencies is selected.
 *
 * @author Иван Дугалић
 */
public class ObservabilityBuildCustomizer implements BuildCustomizer<Build> {

    private static final List<String> MICROMETER_REGISTRY_IDS = Arrays.asList("datadog", "graphite", "influx",
            "new-relic", "prometheus", "axon-micrometer");

    @Override
    public void customize(Build build) {
        if (!build.dependencies().has("actuator")
                && build.dependencies().ids().anyMatch(MICROMETER_REGISTRY_IDS::contains)) {
            build.dependencies().add("actuator");
        }
    }

}
