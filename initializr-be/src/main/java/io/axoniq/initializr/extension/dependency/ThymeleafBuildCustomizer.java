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

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `thymeleaf-extras-spring-security` if
 * `thymeleaf` and `security` is selected.
 *
 * @author Иван Дугалић
 */
public class ThymeleafBuildCustomizer implements BuildCustomizer<Build> {

    @Override
    public void customize(Build build) {
        if (build.dependencies().has("security") && build.dependencies().has("thymeleaf")) {
            build.dependencies().add(
                    "thymeleaf-extras-spring-security",
                    Dependency.withCoordinates("org.thymeleaf.extras", "thymeleaf-extras-springsecurity5")
            );
        }
    }
}
