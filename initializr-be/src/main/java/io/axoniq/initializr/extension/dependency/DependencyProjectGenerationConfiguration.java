/*
 * Copyright (c) 2021. AxonIQ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.axoniq.initializr.extension.dependency;

import io.axoniq.initializr.customcontroller.AxonProjectContributor;
import io.axoniq.initializr.extension.dependency.axon.AxonBuildCustomizer;
import io.axoniq.initializr.extension.dependency.axon.AxonHelpDocumentCustomizer;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.metadata.InitializrMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

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
    @Order(1)
    public AxonHelpDocumentCustomizer axonHelpDocumentCustomizer(InitializrMetadata metadata,
                                                                 ProjectDescription description) {
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

    @Bean
    @Order(1000)
    public AxonProjectContributor axonProjectContributor(ProjectDescription description) {
        return new AxonProjectContributor(description);
    }

    @Bean
    @Order(1001)
    @ConditionalOnRequestedDependency("h2")
    public H2ProjectContributor H2ProjectContributor() {
        return new H2ProjectContributor();
    }

    @Bean
    @Order(1002)
    @ConditionalOnRequestedDependency("postgresql")
    public PostgresProjectContributor postgresProjectContributor() {
        return new PostgresProjectContributor();
    }

    //quartz contributor db properties


}
