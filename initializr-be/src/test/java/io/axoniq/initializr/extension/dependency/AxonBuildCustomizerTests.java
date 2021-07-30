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
