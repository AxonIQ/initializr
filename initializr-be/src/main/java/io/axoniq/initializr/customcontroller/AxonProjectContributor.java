package io.axoniq.initializr.customcontroller;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * A {@link ProjectContributor} that adds properties to an application properties and generates additional resources
 * at the root of the
 * project when the registered description is a {@link AxonProjectDescription} and its
 * custom Axon flags
 *
 * @author Stefan Dragisic
 */
public class AxonProjectContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(AxonProjectContributor.class);

    private final ProjectDescription description;

    public AxonProjectContributor(
            ProjectDescription description) {
        this.description = description;
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        if (this.description instanceof AxonProjectDescription
                && !((AxonProjectDescription) this.description).getAxonServerContext().isEmpty()) {

            AxonProjectDescription projectDescription = (AxonProjectDescription) this.description;

            if (projectDescription.isUsingAxonSaaS()) {
                configureForSaaS(projectRoot, projectDescription);
                return;
            }

            if (projectDescription.isUsingAxonServerSE()) {
                configureForAxonServerSE(projectRoot, projectDescription);
                return;
            }

            if (projectDescription.isUsingAxonServerEE()) {
                configureForAxonServerEE(projectRoot, projectDescription);
            }

        }
    }

    private void configureForSaaS(Path projectRoot, AxonProjectDescription projectDescription) throws IOException {
        renderTemplate("configuration/axon-server/saas/application.properties.mustache",
                "src/main/resources/application.properties",
                projectRoot,
                projectDescription);
        appendToFile("configuration/axon-server/saas/README.md",
                "HELP.md",
                projectRoot);
    }

    private void configureForAxonServerEE(Path projectRoot, AxonProjectDescription projectDescription) throws IOException {
        copyFile("configuration/axon-server/ee/docker/axoniq.license",
                "src/main/docker/axoniq.license",
                projectRoot);
        copyFile("configuration/axon-server/ee/docker/docker-compose.yml",
                "src/main/docker/docker-compose.yml",
                projectRoot);
        renderTemplate("configuration/axon-server/ee/docker/cluster-template.yml.mustache",
                "src/main/docker/cluster-template.yml",
                projectRoot,
                projectDescription);
        renderTemplate("configuration/axon-server/ee/docker/application.properties.mustache",
                "src/main/resources/application.properties",
                projectRoot,
                projectDescription);
        appendToFile("configuration/axon-server/ee/docker/README.md",
                "HELP.md",
                projectRoot);
    }

    private void configureForAxonServerSE(Path projectRoot, AxonProjectDescription projectDescription) throws IOException {
        copyFile("configuration/axon-server/se/docker/docker-compose.yml",
                "src/main/docker/docker-compose.yml",
                projectRoot);
        appendToFile("configuration/axon-server/se/docker/README.md",
                "HELP.md",
                projectRoot);
    }

    private void copyFile(String source, String destination, Path projectRoot) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void renderTemplate(String templateSource, String templateDestination, Path projectRoot, AxonProjectDescription projectDescription) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();

        Mustache m = mf.compile(templateSource);

        Path output = projectRoot.resolve(templateDestination);
        if (!Files.exists(output)) {
            Files.createDirectories(output.getParent());
            Path file = Files.createFile(output);
            FileWriter writer = new FileWriter(file.toFile());

            m.execute(writer, projectDescription).flush();

            writer.close();
        } else {
            FileWriter writer = new FileWriter(output.toFile(), true);
            m.execute(writer, projectDescription).flush();
        }
    }

    private void appendToFile(String sourcePath, String destinationPath, Path projectRoot) throws IOException {
        Resource resource = new ClassPathResource(sourcePath);
        Path output = projectRoot.resolve(destinationPath);
        FileWriter writer = new FileWriter(output.toFile(), true);

        try (Stream<String> stream = Files.lines(resource.getFile().toPath())) {

            stream.forEach(line -> {
                try {
                    writer.append(line);
                    writer.append(System.getProperty("line.separator"));
                } catch (IOException e) {
                    logger.error(e);
                }
            });

        } catch (IOException e) {
            throw e;
        }

        writer.flush();

    }

}