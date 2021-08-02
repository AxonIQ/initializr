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

package io.axoniq.initializr.extension.dependency.axon;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

/**
 * A build customizer for Axon projects that provides explicit selection of the `axon-starter` and `axon-test`
 * dependency.
 *
 * @author Иван Дугалић
 */
public class AxonBuildCustomizer implements BuildCustomizer<Build> {

    private static final String AXON_STARTER = "axon-starter";
    private static final String AXON_TEST = "axon-test";

    @Override
    public void customize(Build build) {

        if (!build.dependencies().has(AXON_STARTER)) {
            build.dependencies().add(AXON_STARTER);
        }

        if (!build.dependencies().has(AXON_TEST)) {
            build.dependencies().add(AXON_TEST);
        }
    }
}
