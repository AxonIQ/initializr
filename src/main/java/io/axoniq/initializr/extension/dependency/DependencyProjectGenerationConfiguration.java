package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * {@link ProjectGenerationConfiguration} for customizations relevant to selected dependencies.
 *
 * @author Иван Дугалић
 */
@ProjectGenerationConfiguration
public class DependencyProjectGenerationConfiguration {

    @Bean
    public AxonBuildCustomizer axonBuildCustomizer() {
        return new AxonBuildCustomizer();
    }
}
