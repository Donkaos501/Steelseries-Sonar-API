package de.donkaos.sonar;

public enum Channel {

    GAME("game"),
    CHAT("chatRender"),
    MEDIA("media"),
    AUX("aux"),
    MASTER("master");

    private final String key;

    Channel(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * Given a key (e.g., "chatRender"), returns the corresponding Channel (CHAT).
     * Throws an IllegalArgumentException if no match is found.
     */
    public static Channel fromKey(String key) {
        for (Channel c : Channel.values()) {
            if (c.key.equalsIgnoreCase(key)) {
                return c;
            }
        }
        throw new IllegalArgumentException("No matching channel for key: " + key);
    }
}
