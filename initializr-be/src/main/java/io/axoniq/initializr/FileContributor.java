package io.axoniq.initializr;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.axoniq.initializr.customcontroller.AxonProjectDescription;
import io.axoniq.initializr.extension.dependency.H2ProjectContributor;
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
 * Contains util methods for file manipulation.
 *
 * @author Stefan Dragisic
 */
public abstract class FileContributor {

    private static final Log logger = LogFactory.getLog(FileContributor.class);

    protected Path projectRoot;

    protected final MustacheFactory mf = new DefaultMustacheFactory();

    protected AxonProjectDescription projectDescription;

    public void appendToFile(String sourcePath, String destinationPath) throws IOException {
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

    public void copyFile(String source, String destination) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public void renderTemplate(String templateSource, String templateDestination) throws IOException {
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

}
