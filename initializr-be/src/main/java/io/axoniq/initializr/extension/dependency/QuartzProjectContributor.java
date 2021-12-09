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

import io.axoniq.initializr.customcontroller.AxonProjectDescription;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

/**
 * A {@link ProjectContributor} that creates quartz the "db/migration" resources directory, migration scripts and
 * application properties when Quartz is selected.
 *
 * @author Ivan Dugalic
 * @author Lucas Campos
 * @author Stefan Dragisic
 */
public class QuartzProjectContributor implements ProjectContributor {

    private static final Log logger = LogFactory.getLog(QuartzProjectContributor.class);

    private Path projectRoot;

    private final AxonProjectDescription projectDescription;

    public QuartzProjectContributor(
            ProjectDescription description) {
        this.projectDescription = (AxonProjectDescription) description;
    }

    @Override
    public void contribute(Path projectRoot) throws IOException {
        this.projectRoot = projectRoot;
        boolean useFlyway = projectDescription.getRequestedDependencies().get("flyway") != null;
        boolean useLiquibase = projectDescription.getRequestedDependencies().get("liquibase") != null;

        if (useFlyway) {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
            String strDate = dateFormat.format(date);

            copyFile("configuration/axon-framework/quartz/flyway/create-quartz-related-tables-baseline.sql",
                     "src/main/resources/db/migration/" + "V" + strDate
                             + "__create-quartz-related-tables-baseline.sql");
        } else if (useLiquibase) {
            copyFile(
                    "configuration/axon-framework/quartz/liquibase/db.changelog-00001-2-create-quartz-related-tables-baseline.xml",
                    "src/main/resources/db/migration/db.changelog-00001-2-create-quartz-related-tables-baseline.xml");
        }

        appendToFile("configuration/axon-framework/quartz/application.properties",
                     "src/main/resources/application.properties");
    }

    private void copyFile(String source, String destination) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
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
