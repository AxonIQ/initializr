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

import io.axoniq.initializr.FileContributor;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A {@link ProjectContributor} that creates the "db/migration" resources directory when Flyway is selected.
 *
 * @author Ivan Dugalic
 * @author Lucas Campos
 * @author Stefan Dragisic
 */
public class FlywayProjectContributor extends FileContributor implements ProjectContributor {

    private Path projectRoot;

    @Override
    public void contribute(Path projectRoot) throws IOException {
        this.projectRoot = projectRoot;
        Path migrationDirectory = projectRoot.resolve("src/main/resources/db/migration");
        Files.createDirectories(migrationDirectory);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String strDate = dateFormat.format(date);

        copyFile("configuration/axon-framework/flyway/create_axon_framework_related_tables_baseline.sql",
                 "src/main/resources/db/migration/" + "V" + strDate
                         + "__create_axon_framework_related_tables_baseline.sql");
    }
}
