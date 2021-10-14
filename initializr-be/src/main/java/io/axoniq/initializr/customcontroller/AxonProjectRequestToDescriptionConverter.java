package io.axoniq.initializr.customcontroller;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.web.project.DefaultProjectRequestToDescriptionConverter;
import io.spring.initializr.web.project.ProjectRequestToDescriptionConverter;

/**
 * To make sure your view of ProjectDescription is made available in the ProjectGenerationContext,
 * we provide a custom ProjectRequestToDescriptionConverter. It should be defined and could reuse
 * DefaultProjectRequestToDescriptionConverter to apply general rules for standard fields.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectRequestToDescriptionConverter
        implements ProjectRequestToDescriptionConverter<AxonProjectRequest> {

    @Override
    public ProjectDescription convert(AxonProjectRequest request, InitializrMetadata metadata) {
        AxonProjectDescription description = new AxonProjectDescription();
        new DefaultProjectRequestToDescriptionConverter().convert(request, description, metadata);
        description.setAxonServerContext(request.getAxonServerContext());
        description.setUsingAxonServer(request.getUsingAxonServer());
        return description;
    }

}
