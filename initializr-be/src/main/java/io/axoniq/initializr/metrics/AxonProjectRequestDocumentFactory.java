package io.axoniq.initializr.metrics;

import io.spring.initializr.actuate.stat.ProjectRequestDocument;
import io.spring.initializr.generator.version.Version;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.web.project.ProjectFailedEvent;
import io.spring.initializr.web.project.ProjectRequest;
import io.spring.initializr.web.project.ProjectRequestEvent;
import io.spring.initializr.web.project.WebProjectRequest;
import io.spring.initializr.web.support.Agent;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory class for AxonProjectRequestDocument.
 *
 * @author Ivan Dugalic
 */
public class AxonProjectRequestDocumentFactory {

    /**
     * Creates AxonProjectRequestDocument based on the spring event of type `ProjectRequestEvent`
     *
     * @param event - a ProjectRequestEvent
     * @return a AxonProjectRequestDocument
     */
    public AxonProjectRequestDocument createDocument(ProjectRequestEvent event) {
        InitializrMetadata metadata = event.getMetadata();
        ProjectRequest request = event.getProjectRequest();
        AxonProjectRequestDocument document = new AxonProjectRequestDocument();
        document.setGenerationTimestamp(event.getTimestamp());
        document.setGroupId(request.getGroupId());
        document.setArtifactId(request.getArtifactId());
        document.setPackageName(request.getPackageName());
        document.setVersion(this.determineVersionInformation(request));
        document.setClient(this.determineClientInformation(request));
        document.setAxonVersion(event.getMetadata().getDependencies().get("axon-starter").getVersion());
        document.setJavaVersion(request.getJavaVersion());
        if (StringUtils.hasText(request.getJavaVersion())
                && metadata.getJavaVersions().get(request.getJavaVersion()) == null) {
            document.triggerError().setJavaVersion(true);
        }

        document.setLanguage(request.getLanguage());
        if (StringUtils.hasText(request.getLanguage()) && metadata.getLanguages().get(request.getLanguage()) == null) {
            document.triggerError().setLanguage(true);
        }

        document.setPackaging(request.getPackaging());
        if (StringUtils.hasText(request.getPackaging())
                && metadata.getPackagings().get(request.getPackaging()) == null) {
            document.triggerError().setPackaging(true);
        }

        document.setType(request.getType());
        document.setBuildSystem(this.determineBuildSystem(request));
        if (StringUtils.hasText(request.getType()) && metadata.getTypes().get(request.getType()) == null) {
            document.triggerError().setType(true);
        }

        List<String> dependencies = new ArrayList<>(request.getDependencies());
        List<String> validDependencies = dependencies.stream()
                                                     .filter((id) -> metadata.getDependencies().get(id) != null)
                                                     .collect(Collectors.toList());

        document.setDependencies(new ProjectRequestDocument.DependencyInformation(validDependencies));
        List<String> invalidDependencies = dependencies.stream()
                                                       .filter((id) -> !validDependencies.contains(id))
                                                       .collect(Collectors.toList());
        if (!invalidDependencies.isEmpty()) {
            document.triggerError().triggerInvalidDependencies(invalidDependencies);
        }

        if (event instanceof ProjectFailedEvent) {
            ProjectRequestDocument.ErrorStateInformation errorState = document.triggerError();
            ProjectFailedEvent failed = (ProjectFailedEvent) event;
            if (failed.getCause() != null) {
                errorState.setMessage(failed.getCause().getMessage());
            }
        }

        return document;
    }

    private String determineBuildSystem(ProjectRequest request) {
        String type = request.getType();
        String[] elements = type.split("-");
        return elements.length == 2 ? elements[0] : null;
    }

    private ProjectRequestDocument.VersionInformation determineVersionInformation(ProjectRequest request) {
        Version version = Version.safeParse(request.getBootVersion());
        return version != null && version.getMajor() != null
                ? new ProjectRequestDocument.VersionInformation(version)
                : null;
    }

    private ProjectRequestDocument.ClientInformation determineClientInformation(ProjectRequest request) {
        if (request instanceof WebProjectRequest) {
            WebProjectRequest webProjectRequest = (WebProjectRequest) request;
            Agent agent = this.determineAgent(webProjectRequest);
            String ip = this.determineIp(webProjectRequest);
            String country = this.determineCountry(webProjectRequest);
            if (agent != null || ip != null || country != null) {
                return new ProjectRequestDocument.ClientInformation(agent, ip, country);
            }
        }

        return null;
    }

    private Agent determineAgent(WebProjectRequest request) {
        String userAgent = (String) request.getParameters().get("user-agent");
        return StringUtils.hasText(userAgent) ? Agent.fromUserAgent(userAgent) : null;
    }

    private String determineIp(WebProjectRequest request) {
        String candidate = (String) request.getParameters().get("cf-connecting-ip");
        return StringUtils.hasText(candidate) ? candidate : (String) request.getParameters().get("x-forwarded-for");
    }

    private String determineCountry(WebProjectRequest request) {
        String candidate = (String) request.getParameters().get("cf-ipcountry");
        return StringUtils.hasText(candidate) && !"xx".equalsIgnoreCase(candidate) ? candidate : null;
    }
}
