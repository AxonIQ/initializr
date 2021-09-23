package io.axoniq.initializr.customcontroller;


import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A {@link ProjectContributor} that adds properties to an application properties and generates additional resources
 * at the root of the
 * project when the registered description is a {@link AxonProjectDescription} and its
 * custom Axon flags
 *
 *  @author Stefan Dragisic
 */
public class AxonProjectContributor implements ProjectContributor {

    private final ProjectDescription description;

    public AxonProjectContributor(
            ProjectDescription description) {
        this.description = description;
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        if (this.description instanceof AxonProjectDescription
                && !((AxonProjectDescription) this.description).getAxonServerContext().isEmpty()) {

            AxonProjectDescription customProjectDescription = (AxonProjectDescription) this.description;

            String relativePath = "src/main/resources/application.properties";
            Path output = projectRoot.resolve(relativePath);
            if (!Files.exists(output)) {
                Files.createDirectories(output.getParent());
                Path file = Files.createFile(output);
                FileWriter writer = new FileWriter(file.toFile());
                //in future improvements we should introduce template engine
                writer.write("axon.axonserver.context=" + customProjectDescription.getAxonServerContext());
                writer.close();
            }
        }
    }

}