package de.donkaos.sonar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import de.donkaos.sonar.json.OutputDevice;
import de.donkaos.sonar.json.SonarSubAppsFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static de.donkaos.sonar.json.SonarSubAppsFetcher.readStreamAsString;

public class SonarAPI {


    private final URL url;
    private final SonarSubAppsFetcher sub_app_fetcher;
    private final SonarFetcher sonar_fetcher;
    private HashMap<String, OutputDevice> output_devices;

    public SonarAPI(SteelSeriesAPI steel_series_api) {
        String gg_encrypted_address = steel_series_api.getCoreProperties().getGgEncryptedAddress();
        String[] parts = gg_encrypted_address.split(":");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);

        SonarSubAppsFetcher sub_app_fetcher = null;
        try {
             sub_app_fetcher = new SonarSubAppsFetcher(new URL("https://" + host + ":" + port));
        } catch (MalformedURLException e){
            throw new RuntimeException("Malformed URL Exception", e);
        }
        this.sub_app_fetcher = sub_app_fetcher;

        String sonar_webserver = sub_app_fetcher.getSonar_app().getMetadata().getWebServerAddress();

        try {
            this.url = new URL(sonar_webserver);
        } catch (MalformedURLException e){
            throw new RuntimeException("Malformed URL Exception", e);
        }

        this.output_devices = loadDevices();
        this.sonar_fetcher = new SonarFetcher(url);
    }

    public SonarAPI() {
        SteelSeriesAPI steel_series_api = new SteelSeriesAPI();
        String gg_encrypted_address = steel_series_api.getCoreProperties().getGgEncryptedAddress();
        String[] parts = gg_encrypted_address.split(":");
        String host = parts[0];
        int port = Integer.parseInt(parts[1]);

        SonarSubAppsFetcher fetcher = null;
        try {
             fetcher = new SonarSubAppsFetcher(new URL("https://" + host + ":" + port));
        } catch (MalformedURLException e){
            throw new RuntimeException("Malformed URL Exception", e);
        }
        this.sub_app_fetcher = fetcher;

        String sonar_webserver = fetcher.getSonar_app().getMetadata().getWebServerAddress();

        try {
            this.url = new URL(sonar_webserver);
        } catch (MalformedURLException e){
            throw new RuntimeException("Malformed URL Exception", e);
        }

        this.output_devices = loadDevices();
        this.sonar_fetcher = new SonarFetcher(url);
    }


    private HashMap<String, OutputDevice> loadDevices() {
        HashMap<String, OutputDevice> devices = new HashMap<>();
        HttpURLConnection connection = null;

        try {
            URL url = new URL(getUrl()+ "/audioDevices");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream in = connection.getInputStream()) {
                String responseJson = readStreamAsString(in);
                Gson gson = new Gson();
                Type listType = new TypeToken<List<OutputDevice>>() {}.getType();
                List<OutputDevice> deviceList = gson.fromJson(responseJson, listType);
                for (OutputDevice device : deviceList) {
                    devices.put(device.getId(), device);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return devices;
    }


    public double getVolume(Channel channel){
        return sonar_fetcher.getVolume(channel);
    }

    public boolean setVolume(Channel channel, double volume) {
        return sonar_fetcher.volumen(channel, volume);
    }

    public boolean setMute(Channel channel,boolean mute) {
        return sonar_fetcher.mute(channel, mute);
    }

    public boolean setOutput(Channel channel, OutputDevice device) {
        return sonar_fetcher.change_output_device(channel, device);
    }

    public boolean setMicVolume(double volume) {
        return sonar_fetcher.mic_volume(volume);
    }

    public boolean setMicMute(Boolean mute) {
        return sonar_fetcher.mic_mute(mute);
    }


    public HashMap<String, OutputDevice> getOutputDevices() {
        return output_devices;
    }

    public SonarFetcher getSonarFetcher() {
        return sonar_fetcher;
    }

    public URL getUrl() {
        return url;
    }

    public SonarSubAppsFetcher getSub_app_fetcher() {
        return sub_app_fetcher;
    }
}
