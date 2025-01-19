package de.donkaos.sonar.json;

public class OutputDevice {

    private String friendlyName;
    private String id;
    private String dataFlow;
    private String role;
    private int channels;
    private String defaultRole;
    private boolean fwUpdateRequired;

    public OutputDevice() {

    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getId() {
        return id;
    }

    public String getEncodeId() {
        return getId()
            .replace("{", "%7B")
            .replace("}", "%7D");
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataFlow() {
        return dataFlow;
    }

    public void setDataFlow(String dataFlow) {
        this.dataFlow = dataFlow;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public String getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(String defaultRole) {
        this.defaultRole = defaultRole;
    }

    public boolean isFwUpdateRequired() {
        return fwUpdateRequired;
    }

    public void setFwUpdateRequired(boolean fwUpdateRequired) {
        this.fwUpdateRequired = fwUpdateRequired;
    }

    @Override
    public String toString() {
        return "OutputDevice{" +
                "friendlyName='" + friendlyName + '\'' +
                ", id='" + id + '\'' +
                ", dataFlow='" + dataFlow + '\'' +
                ", role='" + role + '\'' +
                ", channels=" + channels +
                ", defaultRole='" + defaultRole + '\'' +
                ", fwUpdateRequired=" + fwUpdateRequired +
                '}';
    }
}
