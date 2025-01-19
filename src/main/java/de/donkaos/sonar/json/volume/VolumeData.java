package de.donkaos.sonar.json.volume;

public class VolumeData {
    private Master masters;
    private java.util.Map<String, DeviceVolume> devices;

    public Master getMasters() {
        return masters;
    }

    public void setMasters(Master masters) {
        this.masters = masters;
    }

    public java.util.Map<String, DeviceVolume> getDevices() {
        return devices;
    }

    public void setDevices(java.util.Map<String, DeviceVolume> devices) {
        this.devices = devices;
    }
}


