package io.axoniq.initializr.extension.dependency;

import io.axoniq.initializr.extension.dependency.axon.AxonBuildCustomizer;
import io.axoniq.initializr.extension.dependency.axon.AxonHelpDocumentCustomizer;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.metadata.InitializrMetadata;
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
    public AxonHelpDocumentCustomizer axonHelpDocumentCustomizer(InitializrMetadata metadata, ProjectDescription description) {
        return new AxonHelpDocumentCustomizer(metadata, description);
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

    @Bean
    public ObservabilityBuildCustomizer axonObservabilityBuildCustomizer() {
        return new ObservabilityBuildCustomizer();
    }

    @Bean
    public ThymeleafBuildCustomizer axonThymeleafBuildCustomizer() {
        return new ThymeleafBuildCustomizer();
    }

    @Bean
    @ConditionalOnRequestedDependency("flyway")
    public FlywayProjectContributor flywayProjectContributor() {
        return new FlywayProjectContributor();
    }

    @Bean
    @ConditionalOnRequestedDependency("liquibase")
    public LiquibaseProjectContributor liquibaseProjectContributor() {
        return new LiquibaseProjectContributor();
    }

}