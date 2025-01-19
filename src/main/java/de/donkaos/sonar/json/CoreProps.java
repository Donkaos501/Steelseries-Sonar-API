package de.donkaos.sonar.json;

public class CoreProps {
    private String encryptedAddress;
    private String ggEncryptedAddress;
    private String address;

    public CoreProps(String encryptedAddress, String ggEncryptedAddress, String address) {
        this.encryptedAddress = encryptedAddress;
        this.ggEncryptedAddress = ggEncryptedAddress;
        this.address = address;
    }

    public String getEncryptedAddress() {
        return encryptedAddress;
    }

    public String getGgEncryptedAddress() {
        return ggEncryptedAddress;
    }

    public String getAddress() {
        return address;
    }
}
