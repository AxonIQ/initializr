package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `thymeleaf-extras-spring-security` if `thymeleaf` and `security` is selected.
 *
 * @author Иван Дугалић
 */
public class ThymeleafBuildCustomizer implements BuildCustomizer<Build> {

    @Override
    public void customize(Build build) {
        if (build.dependencies().has("security") && build.dependencies().has("thymeleaf")) {
            build.dependencies().add("thymeleaf-extras-spring-security",
                    Dependency.withCoordinates("org.thymeleaf.extras", "thymeleaf-extras-springsecurity5"));
        }
    }

}
