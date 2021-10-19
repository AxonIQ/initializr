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
 * A {@link ProjectContributor} generates additional project resources based on user request.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(AxonProjectContributor.class);

    private final MustacheFactory mf = new DefaultMustacheFactory();

    private AxonProjectDescription projectDescription;
    private Path projectRoot;

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
        copyFile("configuration/axon-server/ee/docker/docker-compose.yml",
                "src/main/docker/docker-compose.yml");
        renderTemplate("configuration/axon-server/ee/docker/cluster-template.yml.mustache",
                "src/main/docker/cluster-template.yml");
        renderTemplate("configuration/axon-server/ee/docker/application.properties.mustache",
                "src/main/resources/application.properties");
        appendToFile("configuration/axon-server/ee/docker/README.md",
                "HELP.md");
    }

    private void configureForAxonServerSE() throws IOException {
        copyFile("configuration/axon-server/se/docker/docker-compose.yml",
                "src/main/docker/docker-compose.yml");
        appendToFile("configuration/axon-server/se/docker/README.md",
                "HELP.md");
    }

    private void copyFile(String source, String destination) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void renderTemplate(String templateSource, String templateDestination) throws IOException {
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

    private void appendToFile(String sourcePath, String destinationPath) throws IOException {
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