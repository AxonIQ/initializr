package io.axoniq.initializr;

import io.axoniq.initializr.version.VersionInfo;
import io.axoniq.initializr.version.VersionInfoProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Public RESt controller to retrieve version information.
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
