package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `axon-starter` and `axon-test` dependency.
 *
 * @author Иван Дугалић
 */
public class AxonBuildCustomizer implements BuildCustomizer<Build> {

    public static final String AXON_STARTER = "axon-starter";
    public static final String AXON_SPRING_BOOT_STARTER = "axon-spring-boot-starter";
    public static final String AXON_TEST = "axon-test";
    public static final String ORG_AXONFRAMEWORK = "org.axonframework";

    @Override
    public void customize(Build build) {
        DependencyContainer dependencies = build.dependencies();
        if (!dependencies.has(AXON_STARTER)) {
            dependencies.add(AXON_STARTER, ORG_AXONFRAMEWORK, AXON_SPRING_BOOT_STARTER, DependencyScope.COMPILE);
        }
        if (!dependencies.has(AXON_TEST)) {
            dependencies.add(AXON_TEST, ORG_AXONFRAMEWORK, AXON_TEST, DependencyScope.TEST_COMPILE);
        }
    }
}
