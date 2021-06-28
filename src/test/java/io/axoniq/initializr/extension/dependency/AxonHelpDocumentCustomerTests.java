package io.axoniq.initializr.extension.dependency;

import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.io.template.MustacheTemplateRenderer;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.documentation.HelpDocument;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AxonHelpDocumentCustomerTests {

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
        assertEquals(helpDocument.gettingStarted().additionalLinks().getItems().size(),
                this.metadataProvider.get().getDependencies().get("axon-starter").getLinks().size());
    }

    @Test
    public void axonLinksNotAddedToHelpFileIfSelected() {
        MustacheTemplateRenderer renderer = new MustacheTemplateRenderer("sample");
        HelpDocument helpDocument = new HelpDocument(renderer);

        ProjectDescription description = mock(ProjectDescription.class);
        Map<String, Dependency> requestedDependencies = Map.of("axon-starter", Dependency.withCoordinates("org.axonframework", "axon-spring-boot-starter").build());
        when(description.getRequestedDependencies()).thenReturn(requestedDependencies);

        var axonHelpDocCustomizer = new AxonHelpDocumentCustomizer(this.metadataProvider.get(), description);

        //act
        axonHelpDocCustomizer.customize(helpDocument);

        //assert
        assertEquals(helpDocument.gettingStarted().additionalLinks().getItems().size(), 0);

    }


}
