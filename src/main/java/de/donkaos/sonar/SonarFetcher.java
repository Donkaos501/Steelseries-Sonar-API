package de.donkaos.sonar;

import com.google.gson.Gson;
import de.donkaos.sonar.json.OutputDevice;
import de.donkaos.sonar.json.volume.DeviceVolume;
import de.donkaos.sonar.json.volume.VolumeData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static de.donkaos.sonar.json.SonarSubAppsFetcher.readStreamAsString;

public class SonarFetcher {

    private final URL url;

    public SonarFetcher(URL url) {
        this.url = url;
    }

    private boolean fetch(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Length", "0");
            connection.setRequestProperty("Host", url.getHost()+":"+url.getPort());
            connection.setDoOutput(false);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                connection.disconnect();
                return true;
            } else {
                connection.disconnect();
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean volumen(Channel channel, double volume){
        try {
            URL custom_url = new URL(url + Action.VOLUMEN.getPath(channel) + volume);
            return fetch(custom_url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean mute(Channel channel, boolean mute){
        try {
            if (mute) {
                URL custom_url = new URL(url + Action.MUTE.getPath(channel));
                return fetch(custom_url);
            } else {
                URL custom_url = new URL(url + Action.UNMUTE.getPath(channel));
                return fetch(custom_url);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean mic_mute(boolean mute){
        try {
            if (mute) {
                URL custom_url = new URL(url + Action.MUTE_MIC.getPath());
                return fetch(custom_url);
            } else {
                URL custom_url = new URL(url + Action.UNMUTE_MIC.getPath());
                return fetch(custom_url);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean mic_volume(double volume){
        try {
            URL custom_url = new URL(url + Action.VOLUMEN_MIC.getPath() + volume);
            return fetch(custom_url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean change_output_device(Channel channel, OutputDevice output_device){
        try {
            if (channel.equals(Channel.CHAT)){
                System.out.println(url + Action.CHANGE_OUTPUT.getPath("chat") + output_device.getEncodeId());
                URL custom_url = new URL(url + Action.CHANGE_OUTPUT.getPath("chat") + output_device.getEncodeId());

                return fetch(custom_url);
            } else {
                URL custom_url = new URL(url + Action.CHANGE_OUTPUT.getPath(channel) + output_device.getEncodeId());

                return fetch(custom_url);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    public double getVolume(Channel channel) {
        HttpURLConnection connection = null;
        try {
            URL custom_url = new URL(url + "/volumeSettings/classic");
            connection = (HttpURLConnection) custom_url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream in = connection.getInputStream()) {
                String responseJson = readStreamAsString(in);
                Gson gson = new Gson();
                VolumeData volumeData = gson.fromJson(responseJson, VolumeData.class);

                if (channel.equals(Channel.MASTER)) {
                    return volumeData.getMasters().getClassic().getVolume();
                } else {
                    for (Map.Entry<String, DeviceVolume> entry : volumeData.getDevices().entrySet()) {
                        if (entry.getKey().equalsIgnoreCase("chatCapture")){
                            continue;
                        } else if (Channel.fromKey(entry.getKey()).equals(channel)) {
                            return entry.getValue().getClassic().getVolume();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return 0.0;
    }


    public URL getUrl() {
        return url;
    }



}
