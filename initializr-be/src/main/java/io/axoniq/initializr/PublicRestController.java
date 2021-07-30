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

import io.axoniq.initializr.version.VersionInfo;
import io.axoniq.initializr.version.VersionInfoProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Public REST controller to retrieve version information.
 *
 * @author Lucas Campos
 */
@Controller
public class PublicRestController {

    private final VersionInfoProvider versionInfoProvider;

    public PublicRestController(VersionInfoProvider versionInfoProvider) {
        this.versionInfoProvider = versionInfoProvider;
    }

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return "forward:index.html";
    }

    @ResponseBody
    @GetMapping(path = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public VersionInfo versionInfo() {
        return versionInfoProvider.get();
    }
}
