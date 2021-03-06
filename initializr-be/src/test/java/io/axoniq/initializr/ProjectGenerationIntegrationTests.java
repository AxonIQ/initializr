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

package io.axoniq.initializr;

import io.axoniq.initializr.customcontroller.AxonProjectRequest;
import io.axoniq.initializr.customcontroller.AxonProjectRequestToDescriptionConverter;
import io.spring.initializr.generator.buildsystem.BuildSystem;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.buildsystem.maven.MavenBuildSystem;
import io.spring.initializr.generator.language.Language;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.packaging.Packaging;
import io.spring.initializr.generator.packaging.jar.JarPackaging;
import io.spring.initializr.generator.packaging.war.WarPackaging;
import io.spring.initializr.generator.version.Version;
import io.spring.initializr.metadata.DefaultMetadataElement;
import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.metadata.MetadataElement;
import io.spring.initializr.web.project.DefaultProjectRequestToDescriptionConverter;
import io.spring.initializr.web.project.ProjectGenerationInvoker;
import io.spring.initializr.web.project.ProjectRequest;
import io.spring.initializr.web.project.WebProjectRequest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.*;
import org.junit.jupiter.api.io.*;
import org.junit.jupiter.api.parallel.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Parameterized integration tests for project generation.
 * <p>
 * It will combine all available languages(Kotlin, Java), build systems (Maven, Gradle) and packaging (Jar, War) to
 * generate and build the project. It can take up to 7 minutes to run.
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
class ProjectGenerationIntegrationTests {

    private static final Set<Path> mavenHomes = new CopyOnWriteArraySet<>();

    private static final Set<Path> gradleHomes = new CopyOnWriteArraySet<>();

    private final ThreadLocal<Path> mavenHome = ThreadLocal.withInitial(() -> {
        try {
            Path mavenHome = Files.createTempDirectory("maven-home");
            ProjectGenerationIntegrationTests.mavenHomes.add(mavenHome);
            return mavenHome;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    });

    private final ThreadLocal<Path> gradleHome = ThreadLocal.withInitial(() -> {
        try {
            Path gradleHome = Files.createTempDirectory("gradle-home");
            ProjectGenerationIntegrationTests.gradleHomes.add(gradleHome);
            return gradleHome;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    });

    private final ProjectGenerationInvoker<AxonProjectRequest> invoker;

    private final InitializrMetadata metadata;

    ProjectGenerationIntegrationTests(@Autowired ApplicationContext applicationContext,
                                      @Autowired InitializrMetadataProvider metadataProvider) {
        this.invoker = new ProjectGenerationInvoker<>(
                applicationContext, new AxonProjectRequestToDescriptionConverter()
        );
        this.metadata = metadataProvider.get();
    }

    @AfterAll
    static void deleteMavenAndGradleHomes() {
        for (Path mavenHome : mavenHomes) {
            try {
                FileSystemUtils.deleteRecursively(mavenHome);
            } catch (IOException ex) {
                // Continue
            }
        }
        for (Path gradleHome : gradleHomes) {
            try {
                FileSystemUtils.deleteRecursively(gradleHome);
            } catch (IOException ex) {
                // Continue
            }
        }
    }

    Stream<Arguments> parameters() {
        List<Version> bootVersions = this.metadata.getBootVersions().getContent().stream()
                                                  .map((element) -> Version.parse(element.getId()))
                                                  .collect(Collectors.toList());
        String defaultJvmVersion = this.metadata.getJavaVersions().getContent().stream()
                                                .filter(DefaultMetadataElement::isDefault).map(MetadataElement::getId)
                                                .findAny().orElse("11");
        List<Packaging> packagings = Arrays.asList(new JarPackaging(), new WarPackaging());
        List<Language> languages = Arrays.asList(Language.forId(KotlinLanguage.ID, defaultJvmVersion),
                                                 Language.forId(JavaLanguage.ID, defaultJvmVersion));
        BuildSystem maven = BuildSystem.forId(MavenBuildSystem.ID);
        BuildSystem gradleGroovy = BuildSystem.forIdAndDialect(GradleBuildSystem.ID, GradleBuildSystem.DIALECT_GROOVY);
        BuildSystem gradleKotlin = BuildSystem.forIdAndDialect(GradleBuildSystem.ID, GradleBuildSystem.DIALECT_KOTLIN);
        List<Arguments> configurations = new ArrayList<>();
        for (Version bootVersion : bootVersions) {
            for (Packaging packaging : packagings) {
                for (Language language : languages) {
                    configurations.add(Arguments.arguments(bootVersion, packaging, language, maven));
                    configurations.add(Arguments.arguments(
                            bootVersion, packaging, language,
                            (language.id().equals(KotlinLanguage.ID)) ? gradleKotlin : gradleGroovy
                    ));
                }
            }
        }
        return configurations.stream();
    }

    @ParameterizedTest(name = "{0} - {1} - {2} - {3}")
    @MethodSource("parameters")
    void projectBuilds(Version bootVersion, Packaging packaging, Language language, BuildSystem buildSystem,
                       @TempDir Path directory) throws IOException, InterruptedException {
       // WebProjectRequest request = new WebProjectRequest();
        AxonProjectRequest request = new AxonProjectRequest();
        request.setBootVersion(bootVersion.toString());
        request.setLanguage(language.id());
        request.setPackaging(packaging.id());
        request.setType(buildSystem.id() + "-project");
        request.setGroupId("com.example");
        request.setArtifactId("demo");
        request.setApplicationName("DemoApplication");
        request.setDependencies(Arrays.asList("axon-starter",
                                              "axon-test",
                                              "axon-micrometer",
                                              "axon-kotlin",
                                              "axon-reactor-starter",
                                              "axon-tracing-starter",
                                              "axon-amqp-starter",
                                              "axon-jgroups-starter",
                                              "axon-springcloud-starter",
                                              "axon-mongo",
                                              "actuator",
                                              "configuration-processor",
                                              "web",
                                              "thymeleaf",
                                              "data-jpa",
                                              "h2",
                                              "testcontainers",
                                              "devtools",
                                              "lombok",
                                              "prometheus"));
        Path project = this.invoker.invokeProjectStructureGeneration(request).getRootDirectory();
        ProcessBuilder processBuilder = createProcessBuilder(buildSystem);
        processBuilder.directory(project.toFile());
        Path output = Files.createTempFile(directory, "output-", ".log");
        processBuilder.redirectError(output.toFile());
        processBuilder.redirectOutput(output.toFile());
        assertThat(processBuilder.start().waitFor())
                .describedAs(String.join("\n", Files.readAllLines(output)))
                .isEqualTo(0);
    }

    private ProcessBuilder createProcessBuilder(BuildSystem buildSystem) {
        if (buildSystem.id().equals(new MavenBuildSystem().id())) {
            Path mavenHome = this.mavenHome.get();

            List<String> commands = new ArrayList<>();
            if (System.getProperty("os.name").startsWith("Windows")) {
                commands.add("mvnw.cmd");
            } else {
                commands.add("./mvnw");
            }
            commands.add("-Dmaven.repo.local=" + mavenHome.resolve("repository").toFile());
            commands.add("package");

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.environment().put("MAVEN_USER_HOME", mavenHome.toFile().getAbsolutePath());
            return processBuilder;
        }
        if (buildSystem.id().equals(new GradleBuildSystem().id())) {
            Path gradleHome = this.gradleHome.get();
            List<String> commands = new ArrayList<>();
            if (System.getProperty("os.name").startsWith("Windows")) {
                commands.add("cmd.exe");
                commands.add("/C");
                commands.add("gradlew.bat");
            } else {
                commands.add("./gradlew");
            }
            commands.add("--no-daemon");
            commands.add("build");

            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.environment().put("GRADLE_USER_HOME", gradleHome.toFile().getAbsolutePath());
            return processBuilder;
        }
        throw new IllegalStateException();
    }
}
