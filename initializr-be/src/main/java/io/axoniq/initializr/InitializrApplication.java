package io.axoniq.initializr;

import io.axoniq.initializr.metrics.AxonProjectRequestDocumentFactory;
import io.axoniq.initializr.metrics.ProjectGenerationMonitor;
import io.axoniq.initializr.version.VersionInfoProvider;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Main Initializr application.
 *
 * @author Ivan Dugalic
 */
@EnableAutoConfiguration
@SpringBootConfiguration
@EnableCaching
public class InitializrApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializrApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean(VersionInfoProvider.class)
    public VersionInfoProvider versionInfoProvider() {
        return new VersionInfoProvider();
    }

    @Bean
    public PublicRestController homeController(VersionInfoProvider versionInfoProvider) {
        return new PublicRestController(versionInfoProvider);
    }

    @Bean
    ProjectGenerationMonitor projectGenerationMonitor(MeterRegistry meterRegistry) {
        return new ProjectGenerationMonitor(new AxonProjectRequestDocumentFactory(), meterRegistry);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }
}
