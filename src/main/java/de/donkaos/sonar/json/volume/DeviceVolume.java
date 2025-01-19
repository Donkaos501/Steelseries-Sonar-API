package de.donkaos.sonar.json.volume;


public class DeviceVolume {
    private StreamData stream;
    private ClassicVolume classic;

    public StreamData getStream() {
        return stream;
    }

    public void setStream(StreamData stream) {
        this.stream = stream;
    }

    public ClassicVolume getClassic() {
        return classic;
    }

    public void setClassic(ClassicVolume classic) {
        this.classic = classic;
    }
}
