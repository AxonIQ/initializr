package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `axon-kotlin` dependency.
 * <p>
 * This customizer should run only on Kotlin language selected (@ConditionalOnLanguage("kotlin"))
 *
 * @author Иван Дугалић
 */
public class KotlinBuildCustomizer implements BuildCustomizer<Build> {

    public static final String AXON_KOTLIN_ARTIFACT = "axon-kotlin";
    public static final String AXON_KOTLIN_GROUP = "org.axonframework.extensions.kotlin";

    @Override
    public void customize(Build build) {
        DependencyContainer dependencies = build.dependencies();
        if (!dependencies.has(AXON_KOTLIN_ARTIFACT)) {
            dependencies.add(AXON_KOTLIN_ARTIFACT, AXON_KOTLIN_GROUP, AXON_KOTLIN_ARTIFACT, DependencyScope.COMPILE);
        }
    }
}
