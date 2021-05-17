package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
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

    @Bean
    @ConditionalOnLanguage(KotlinLanguage.ID)
    public KotlinBuildCustomizer axonKotlinBuildCustomizer() {
        return new KotlinBuildCustomizer();
    }

    @Bean
    public AMQPBuildCustomizer axonAMQPBuildCustomizer() {
        return new AMQPBuildCustomizer();
    }

    @Bean
    public KafkaBuildCustomizer axonKafkaBuildCustomizer() {
        return new KafkaBuildCustomizer();
    }

}
