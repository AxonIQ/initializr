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

import io.axoniq.initializr.extension.dependency.axon.AxonBuildCustomizer;
import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyContainer;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;

public class AxonBuildCustomizerTests {

    private static final String AXON_STARTER = "axon-starter";
    private static final String AXON_TEST = "axon-test";

    @Test
    void axonStarterAndTestAreAddedByDefault() {
        //arrange
        Build build = mock(Build.class);
        DependencyContainer container = mock(DependencyContainer.class);
        when(build.dependencies()).thenReturn(container);
        when(container.has(AXON_STARTER)).thenReturn(false);
        when(container.has(AXON_TEST)).thenReturn(false);

        var axonBuildCustomizer = new AxonBuildCustomizer();

        //act
        axonBuildCustomizer.customize(build);

        //assert
        verify(container, times(1)).add(AXON_STARTER);
        verify(container, times(1)).add(AXON_TEST);
    }

    @Test
    void axonStarterAndTestAreNotAddedIfAlreadySelected() {
        //arrange
        Build build = mock(Build.class);
        DependencyContainer container = mock(DependencyContainer.class);
        when(build.dependencies()).thenReturn(container);
        when(container.has(AXON_STARTER)).thenReturn(true);
        when(container.has(AXON_TEST)).thenReturn(true);

        var axonBuildCustomizer = new AxonBuildCustomizer();

        //act
        axonBuildCustomizer.customize(build);

        //assert
        verify(container, times(0)).add(AXON_STARTER);
        verify(container, times(0)).add(AXON_TEST);
    }
}
