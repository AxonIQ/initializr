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

package io.axoniq.initializr.metrics;

import io.axoniq.initializr.customcontroller.AxonProjectRequest;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.spring.initializr.web.project.ProjectRequestEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates micrometer metrics for each project generated.
 *
 * @author Ivan Dugalic
 * @author Stefan Dragisic
 */
public class ProjectGenerationMonitor {

    private static final Log logger = LogFactory.getLog(ProjectGenerationMonitor.class);

    private final AxonProjectRequestDocumentFactory documentFactory;
    private final MeterRegistry meterRegistry;
    private static final String GENERATED_PROJECTS_COUNTER = "axon.initializr.counter";

    public ProjectGenerationMonitor(AxonProjectRequestDocumentFactory documentFactory, MeterRegistry meterRegistry) {
        this.documentFactory = documentFactory;
        this.meterRegistry = meterRegistry;
    }

    private List<Tag> tagsBuilder(AxonProjectRequestDocument document,ProjectRequestEvent event, String dependency) {
        List<Tag> tags = new ArrayList<>();
        if (document.getLanguage() != null) {
            tags.add(Tag.of("language", document.getLanguage()));
        }
        if (document.getBuildSystem() != null) {
            tags.add(Tag.of("buildSystem", document.getBuildSystem()));
        }
        if (document.getType() != null) {
            tags.add(Tag.of("type", document.getType()));
        }
        if (document.getAxonVersion() != null) {
            tags.add(Tag.of("axonVersion", document.getAxonVersion()));
        }
        if (document.getJavaVersion() != null) {
            tags.add(Tag.of("javaVersion", document.getJavaVersion()));
        }
        if (document.getVersion() != null && document.getVersion().getId() != null) {
            tags.add(Tag.of("springBootVersion", document.getVersion().getId()));
        }
        if (document.getClient() != null && document.getClient().getId() != null) {
            tags.add(Tag.of("client", document.getClient().getId()));
        }
        tags.add(Tag.of("dependency", dependency));
        String usingAxonServer = ((AxonProjectRequest) event.getProjectRequest()).getUsingAxonServer();
        tags.add(Tag.of("axonServer", usingAxonServer));
        return tags;
    }

    @EventListener
    @Async
    public void handleEvent(ProjectRequestEvent event) {
        try {
            AxonProjectRequestDocument document = this.documentFactory.createDocument(event);
            if (logger.isDebugEnabled()) {
                logger.debug("Publishing metrics for" + document);
            }

            event
                    .getProjectRequest()
                    .getDependencies()
                    .forEach(dep -> {
                        meterRegistry.counter(GENERATED_PROJECTS_COUNTER, tagsBuilder(document, event, dep))
                                     .increment();
                    });

        } catch (Exception ex) {
            logger.warn("Failed to publish metrics", ex);
        }
    }
}
