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
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `axon-kotlin` dependency.
 * <p>
 * This customizer should run only on Kotlin language selected (@ConditionalOnLanguage("kotlin"))
 *
 * @author Иван Дугалић
 */
public class KotlinBuildCustomizer implements BuildCustomizer<Build> {

    public static final String AXON_KOTLIN_ARTIFACT = "axon-kotlin";
    public static final String AXON_KOTLIN_GROUP = "org.axonframework.extensions.kotlin";

    @Override
    public void customize(Build build) {
        DependencyContainer dependencies = build.dependencies();
        if (!dependencies.has(AXON_KOTLIN_ARTIFACT)) {
            dependencies.add(AXON_KOTLIN_ARTIFACT, AXON_KOTLIN_GROUP, AXON_KOTLIN_ARTIFACT, DependencyScope.COMPILE);
        }
    }
}
