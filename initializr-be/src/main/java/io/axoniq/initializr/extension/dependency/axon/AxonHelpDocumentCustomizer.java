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

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.documentation.HelpDocument;
import io.spring.initializr.generator.spring.documentation.HelpDocumentCustomizer;
import io.spring.initializr.metadata.InitializrMetadata;

/**
 * A {@link HelpDocumentCustomizer} implementation adding Axon help documentation.
 *
 * @author Ben Runchey
 */
public class AxonHelpDocumentCustomizer implements HelpDocumentCustomizer {

    private static final String AXON_STARTER = "axon-starter";
    private static final String AXON_TEST = "axon-test";

    private final InitializrMetadata metadata;
    private final ProjectDescription description;

    public AxonHelpDocumentCustomizer(InitializrMetadata metadata, ProjectDescription description) {
        this.metadata = metadata;
        this.description = description;
    }

    @Override
    public void customize(HelpDocument helpDocument) {
        addLinks(AXON_STARTER, helpDocument);
        addLinks(AXON_TEST, helpDocument);
    }

    public void addLinks(String dependencyId, HelpDocument helpDocument) {
        //if the dependencyId is a "requested" dependency the links will be added by the default RequestedDependenciesHelpDocumentCustomizer
        //so we can skip this.
        if (this.description.getRequestedDependencies().containsKey(dependencyId)) {
            return;
        }

        metadata.getDependencies().get(dependencyId).getLinks().forEach(link -> {
            var x = helpDocument.gettingStarted()
                                .additionalLinks()
                                .getItems()
                                .stream()
                                .filter(l -> l.getHref().equals(link.getHref()));

            var existingLink = x.findFirst().orElse(null);

            if (existingLink == null) {
                helpDocument.gettingStarted().addAdditionalLink(link.getHref(), link.getDescription());
            }
        });
    }
}
