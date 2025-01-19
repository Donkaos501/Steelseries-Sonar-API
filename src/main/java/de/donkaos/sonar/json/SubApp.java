package de.donkaos.sonar.json;

public class SubApp {
    private String name;
    private boolean isEnabled;
    private boolean isReady;
    private boolean isRunning;
    private int exitCode;
    private boolean shouldAutoStart;
    private boolean isWindowsSupported;
    private boolean isMacSupported;
    private boolean toggleViaSettings;
    private boolean isBrowserViewSupported;
    private Metadata metadata;
    private SecretMetadata secretMetadata;

    public String getName() {
        return name;
    }
    public Metadata getMetadata() {
        return metadata;
    }
    // ... plus other getters ...
}
