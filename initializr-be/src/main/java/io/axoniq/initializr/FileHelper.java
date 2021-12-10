package io.axoniq.initializr;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import io.axoniq.initializr.customcontroller.AxonProjectDescription;
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
public abstract class FileHelper {

    private static final Log logger = LogFactory.getLog(FileHelper.class);

    protected Path projectRoot;

    protected final MustacheFactory mf = new DefaultMustacheFactory();

    protected AxonProjectDescription projectDescription;

    /**
     * Appends content from source file to destination file content.
     *
     * @param sourcePath path to the source file
     * @param destinationPath path to the destination file
     */
    protected void appendToFile(String sourcePath, String destinationPath) throws IOException {
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
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw e;
        }
        writer.flush();
    }

    /**
     * Copies source file content to destination.
     *
     * @param source path to the source file
     * @param destination path to the destination file
     */
    protected void copyFile(String source, String destination) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Renders template file to destination file.
     * Uses project description to access variables needed to render the template.
     *
     * @param templateSource path to template file
     * @param templateDestination path to the destination output file
     */
    protected void renderTemplate(String templateSource, String templateDestination) throws IOException {
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
