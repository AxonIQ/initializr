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