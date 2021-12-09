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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * A {@link ProjectContributor} that creates the "db/changelog" resources directory when Liquibase is selected.
 *
 * @author Ivan Dugalic
 * @author Lucas Campos
 * @author Stefan Dragisic
 */
public class LiquibaseProjectContributor implements ProjectContributor {

    private Path projectRoot;

    @Override
    public void contribute(Path projectRoot) throws IOException {
        this.projectRoot = projectRoot;
        Path changelogDirectory = this.projectRoot.resolve("src/main/resources/db/changelog");
        Files.createDirectories(changelogDirectory);

        copyFile("configuration/axon-framework/liquibase/db.changelog-00001-0-create-axon-framework-related-tables-baseline.xml",
                 "src/main/resources/db/migration/db.changelog-00001-0-create-axon-framework-related-tables-baseline.xml");

    }

    private void copyFile(String source, String destination) throws IOException {
        Resource resource = new ClassPathResource(source);
        Path destinationPath = projectRoot.resolve(destination);

        if (!Files.exists(destinationPath)) {
            Files.createDirectories(destinationPath.getParent());
        }

        Files.copy(resource.getFile().toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }
}
