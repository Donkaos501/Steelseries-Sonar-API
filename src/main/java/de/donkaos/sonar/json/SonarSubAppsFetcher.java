package de.donkaos.sonar.json;

import com.google.gson.Gson;
import de.donkaos.sonar.ssl.TrustAllCerts;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SonarSubAppsFetcher {

    private URL url;
    private SubAppsRoot sub_apps_root;
    private SubApp sonar_app;

    public SonarSubAppsFetcher(URL base_url) {
        try {
            this.url = new URL(base_url, "/subApps");
            SSLContext sslContext = TrustAllCerts.createInsecureSSLContext();
            SSLContext.setDefault(sslContext);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslContext.getSocketFactory());
            try (InputStream in = connection.getInputStream()) {
                String responseBody = readStreamAsString(in);
                SubAppsRoot root = new Gson().fromJson(responseBody, SubAppsRoot.class);
                this.sub_apps_root = root;
                SubApp sonar_app = root.getSubApps().get("sonar");
                this.sonar_app = sonar_app;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void main(String[] args) {
        try {
            // 1. Configure the trust-all SSL context
            SSLContext sslContext = TrustAllCerts.createInsecureSSLContext();
            SSLContext.setDefault(sslContext);  // optional

            // 2. Create the connection
            URL url = new URL("https://127.0.0.1:6327/subApps");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslContext.getSocketFactory());

            // 3. Read response
            try (InputStream in = connection.getInputStream()) {
                // Convert the body to a String using the helper method
                String responseBody = readStreamAsString(in);

                // 4. Parse JSON with GSON
                SubAppsRoot root = new Gson().fromJson(responseBody, SubAppsRoot.class);

                // 5. Extract subApps.sonar.metadata.webServerAddress
                SubApp sonarApp = root.getSubApps().get("sonar");
                if (sonarApp != null && sonarApp.getMetadata() != null) {
                    String sonarWebServerAddress = sonarApp.getMetadata().getWebServerAddress();
                    System.out.println("Sonar webServerAddress = " + sonarWebServerAddress);
                } else {
                    System.out.println("Sonar subApp not found or missing metadata.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static String readStreamAsString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int bytesRead;
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        return new String(buffer.toByteArray(), StandardCharsets.UTF_8);
    }

    public URL getUrl() {
        return url;
    }

    public SubAppsRoot getSub_apps_root() {
        return sub_apps_root;
    }

    public SubApp getSonar_app() {
        return sonar_app;
    }
}
