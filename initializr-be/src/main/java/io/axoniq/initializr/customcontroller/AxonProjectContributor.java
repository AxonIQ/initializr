package io.axoniq.initializr.customcontroller;


import io.axoniq.initializr.FileHelper;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Path;

/**
 * A {@link ProjectContributor} generates additional project resources based on user request.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectContributor extends FileHelper implements ProjectContributor {

    public AxonProjectContributor(
            ProjectDescription description) {
        this.projectDescription = (AxonProjectDescription) description;
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
            this.projectRoot = projectRoot;

            switch (projectDescription.getUsingAxonServer().toUpperCase()) {
                case "EE" : configureForAxonServerEE();
                break;
                case "SE" : configureForAxonServerSE();
                break;
                case "CLOUD" : configureForAxonServerCloud();
                default:
            }
    }

    private void configureForAxonServerCloud() throws IOException {
        renderTemplate("configuration/axon-server/saas/application.properties.mustache",
                "src/main/resources/application.properties");
        appendToFile("configuration/axon-server/saas/README.md",
                "HELP.md");
    }

    private void configureForAxonServerEE() throws IOException {
        copyFile("configuration/axon-server/ee/docker/axoniq.license",
                "src/main/docker/axoniq.license");
        copyFile("configuration/axon-server/ee/docker/docker-compose-axonserver-ee.yml",
                "src/main/docker/docker-compose-axonserver-ee.yml");
        renderTemplate("configuration/axon-server/ee/docker/cluster-template.yml.mustache",
                "src/main/docker/cluster-template.yml");
        renderTemplate("configuration/axon-server/ee/docker/application.properties.mustache",
                "src/main/resources/application.properties");
        appendToFile("configuration/axon-server/ee/docker/README.md",
                "HELP.md");
    }

    private void configureForAxonServerSE() throws IOException {
        copyFile("configuration/axon-server/se/docker/docker-compose-axonserver-se.yml",
                "src/main/docker/docker-compose-axonserver-se.yml");
        appendToFile("configuration/axon-server/se/docker/README.md",
                "HELP.md");
    }
}
