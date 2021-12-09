/*
 * Copyright (c) 2021. AxonIQ
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * A {@link ProjectContributor} that creates the adds default H2 properties when H2 is selected.
 *
 * @author Lucas Campos
 * @author Stefan Dragisic
 */
public class H2ProjectContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(H2ProjectContributor.class);

    private Path projectRoot;

    @Override
    public void contribute(Path projectRoot) throws IOException {
        this.projectRoot = projectRoot;

        appendToFile("configuration/axon-framework/h2/application.properties",
                     "src/main/resources/application.properties");
    }

    private void appendToFile(String sourcePath, String destinationPath) throws IOException {
        Resource resource = new ClassPathResource(sourcePath);
        Path output = projectRoot.resolve(destinationPath);
        FileWriter writer = new FileWriter(output.toFile(), true);
        writer.append(System.getProperty("line.separator"));
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
