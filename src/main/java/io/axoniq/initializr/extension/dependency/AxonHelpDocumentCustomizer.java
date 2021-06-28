package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.documentation.HelpDocument;
import io.spring.initializr.generator.spring.documentation.HelpDocumentCustomizer;
import io.spring.initializr.metadata.InitializrMetadata;

public class AxonHelpDocumentCustomizer implements HelpDocumentCustomizer {


    private final InitializrMetadata metadata;
    private final ProjectDescription description;

    public AxonHelpDocumentCustomizer(InitializrMetadata metadata, ProjectDescription description) {
        this.metadata = metadata;
        this.description = description;
        ;
    }

    @Override
    public void customize(HelpDocument helpDocument) {

        //if axon-starter is a selected dependency the links will be added by the default RequestedDependenciesHelpDocumentCustomizer
        //so we can skip this.
        if (this.description.getRequestedDependencies().containsKey("axon-starter")) {
            return;
        }

        metadata.getDependencies().get("axon-starter").getLinks().forEach(link -> {
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
