package io.axoniq.initializr.version;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.info")
public class VersionInfoProvider {

    private String name = "Cloud Console";
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