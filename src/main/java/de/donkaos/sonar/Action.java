package de.donkaos.sonar;

public enum Action {

    //     /AudioDeviceRouting
    MUTE("/volumeSettings/classic/CHANNEL/Mute/true"),
    UNMUTE("/volumeSettings/classic/CHANNEL/Mute/false"),
    VOLUMEN("/volumeSettings/classic/CHANNEL/Volume/"),
    MUTE_MIC("/volumeSettings/classic/chatCapture/Mute/true"),
    UNMUTE_MIC("/volumeSettings/classic/chatCapture/Mute/false"),
    VOLUMEN_MIC("/volumeSettings/classic/chatCapture/Volume/"),
    CHANGE_OUTPUT("/classicRedirections/CHANNEL/deviceId/"); // %7B0.0.0.00000000%7D.%7B058f23b7-e74a-4c87-a395-df793898f482%7D


    private final String path;

    Action(String path){
        this.path = path;
    }

    public String getPath(Channel channel) {
        return path.replace("CHANNEL", channel.getKey());
    }

    public String getPath(String custom){
        return path.replace("CHANNEL", custom);
    }

    public String getPath() {
        return path;
    }
}
