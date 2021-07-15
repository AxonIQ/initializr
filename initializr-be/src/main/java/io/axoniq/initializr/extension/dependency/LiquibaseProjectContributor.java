package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A {@link ProjectContributor} that creates the "db/changelog" resources directory when
 * Liquibase is selected.
 *
 * @author Ivan Dugalic
 */
public class LiquibaseProjectContributor implements ProjectContributor {

    @Override
    public void contribute(Path projectRoot) throws IOException {
        Path changelogDirectory = projectRoot.resolve("src/main/resources/db/changelog");
        Files.createDirectories(changelogDirectory);
    }

}
