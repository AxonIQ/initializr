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

/**
 * Data object holder for version information.
 *
 * @author Lucas Campos
 */
public class VersionInfo {

    private final String productName;
    private final String version;
    private final String lastBuildTime;

    public VersionInfo(String productName, String version, String lastBuildTime) {
        this.productName = productName;
        this.version = version;
        this.lastBuildTime = lastBuildTime;
    }

    public String getProductName() {
        return productName;
    }

    public String getVersion() {
        return version;
    }

    public String getLastBuildTime() {
        return lastBuildTime;
    }
}