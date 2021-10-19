package io.axoniq.initializr.customcontroller;

import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.web.controller.ProjectGenerationController;
import io.spring.initializr.web.project.ProjectGenerationInvoker;

import java.util.Map;

/**
 * A custom {@link ProjectGenerationController} that binds request attributes to a
 * {@link AxonProjectRequest}.
 *
 * @author Stefan Dragisic
 */
public class AxonProjectGenerationController extends ProjectGenerationController<AxonProjectRequest> {

    public AxonProjectGenerationController(InitializrMetadataProvider metadataProvider,
                                           ProjectGenerationInvoker<AxonProjectRequest> projectGenerationInvoker) {
        super(metadataProvider, projectGenerationInvoker);
    }

    @Override
    public AxonProjectRequest projectRequest(Map<String, String> headers) {
        AxonProjectRequest request = new AxonProjectRequest();
        request.getParameters().putAll(headers);
        request.initialize(getMetadata());
        return request;
    }

}

