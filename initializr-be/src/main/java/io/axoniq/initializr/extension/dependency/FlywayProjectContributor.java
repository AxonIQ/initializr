package io.axoniq.initializr.extension.dependency;


import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A {@link ProjectContributor} that creates the "db/migration" resources directory when
 * Flyway is selected.
 *
 * @author Ivan Dugalic
 */
public class FlywayProjectContributor implements ProjectContributor {

    @Override
    public void contribute(Path projectRoot) throws IOException {
        Path migrationDirectory = projectRoot.resolve("src/main/resources/db/migration");
        Files.createDirectories(migrationDirectory);
    }

}
