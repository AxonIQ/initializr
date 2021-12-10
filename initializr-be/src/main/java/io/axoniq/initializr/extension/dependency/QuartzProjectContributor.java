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

import io.axoniq.initializr.FileHelper;
import io.axoniq.initializr.customcontroller.AxonProjectDescription;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A {@link ProjectContributor} that creates the "db/migration" resources directory and migration script (if applicable), and the
 * application properties when Quartz is selected.
 *
 * @author Lucas Campos
 * @author Stefan Dragisic
 */
public class QuartzProjectContributor extends FileHelper implements ProjectContributor {

    private final AxonProjectDescription projectDescription;

    public QuartzProjectContributor(ProjectDescription description) {
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
}
