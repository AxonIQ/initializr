package io.axoniq.initializr;

import io.axoniq.initializr.metrics.AxonProjectRequestDocumentFactory;
import io.axoniq.initializr.metrics.ProjectGenerationMonitor;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@SpringBootConfiguration
@EnableCaching
public class InitializrApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializrApplication.class, args);
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }

    @Bean
    ProjectGenerationMonitor projectGenerationMonitor(MeterRegistry meterRegistry) {
        return new ProjectGenerationMonitor(new AxonProjectRequestDocumentFactory(), meterRegistry);
    }

}
