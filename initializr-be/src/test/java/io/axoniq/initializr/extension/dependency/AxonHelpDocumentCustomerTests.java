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

import io.axoniq.initializr.extension.dependency.axon.AxonHelpDocumentCustomizer;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.documentation.HelpDocument;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AxonHelpDocumentCustomerTests {

    private static final String AXON_STARTER = "axon-starter";
    private static final String AXON_TEST = "axon-test";

    @Autowired
    private InitializrMetadataProvider metadataProvider;

    @Test
    public void axonLinksAddedToHelpFile() {
        MustacheTemplateRenderer renderer = new MustacheTemplateRenderer("sample");
        HelpDocument helpDocument = new HelpDocument(renderer);

        ProjectDescription description = mock(ProjectDescription.class);
        Map<String, Dependency> requestedDependencies = Map.of();
        when(description.getRequestedDependencies()).thenReturn(requestedDependencies);

        var axonHelpDocCustomizer = new AxonHelpDocumentCustomizer(this.metadataProvider.get(), description);

        //act
        axonHelpDocCustomizer.customize(helpDocument);

        //assert
        var totalLinks = getDependencyLinkCount(AXON_STARTER) +
                getDependencyLinkCount(AXON_TEST);

        assertEquals(totalLinks, helpDocument.gettingStarted().additionalLinks().getItems().size());
    }

    @Test
    public void axonLinksNotAddedToHelpFileIfSelected() {
        MustacheTemplateRenderer renderer = new MustacheTemplateRenderer("sample");
        HelpDocument helpDocument = new HelpDocument(renderer);

        ProjectDescription description = mock(ProjectDescription.class);
        Map<String, Dependency> requestedDependencies = Map.of(
                AXON_STARTER, Dependency.withCoordinates("org.axonframework", "axon-spring-boot-starter").build(),
                AXON_TEST, Dependency.withCoordinates("org.axonframework", "axon-test").build()
        );
        when(description.getRequestedDependencies()).thenReturn(requestedDependencies);

        var axonHelpDocCustomizer = new AxonHelpDocumentCustomizer(this.metadataProvider.get(), description);

        //act
        axonHelpDocCustomizer.customize(helpDocument);

        //assert
        assertEquals(helpDocument.gettingStarted().additionalLinks().getItems().size(), 0);
    }

    private int getDependencyLinkCount(String dependencyId) {
        return this.metadataProvider.get().getDependencies().get(dependencyId).getLinks().size();
    }
}
