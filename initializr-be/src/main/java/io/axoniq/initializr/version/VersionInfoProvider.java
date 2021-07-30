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

package io.axoniq.initializr.version;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * {@link ConfigurationProperties} object for {@link VersionInfo}.
 *
 * @author Lucas Campos
 */
@ConfigurationProperties(prefix = "app.info")
public class VersionInfoProvider {

    private String name = "Initializr";
    private String version = "Unknown";
    private String timestamp = "Unknown";

    public VersionInfo get() {
        return new VersionInfo(name, version, timestamp);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
