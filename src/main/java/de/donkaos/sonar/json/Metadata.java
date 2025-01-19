package de.donkaos.sonar.json;

public class Metadata {
    private String encryptedWebServerAddress;
    private String webServerAddress;
    private String offlineFrontendAddress;
    private String onlineFrontendAddress;

    public String getEncryptedWebServerAddress() {
        return encryptedWebServerAddress;
    }
    public String getWebServerAddress() {
        return webServerAddress;
    }

    public String getOfflineFrontendAddress() {
        return offlineFrontendAddress;
    }

    public String getOnlineFrontendAddress() {
        return onlineFrontendAddress;
    }
}
