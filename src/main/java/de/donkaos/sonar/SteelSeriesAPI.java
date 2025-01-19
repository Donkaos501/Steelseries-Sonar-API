package de.donkaos.sonar;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import de.donkaos.sonar.json.CoreProps;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SteelSeriesAPI {

    private final File core_properties_file = new File("C:\\ProgramData\\SteelSeries\\GG\\coreProps.json");

    private final CoreProps core_properties;


    public SteelSeriesAPI(CoreProps coreProperties) {
        core_properties = coreProperties;
    }

    public SteelSeriesAPI(File core_properties_file_json) {
        try (JsonReader reader = new JsonReader(new FileReader(core_properties_file_json))) {
            core_properties = new Gson().fromJson(reader, CoreProps.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read or parse coreProps.json", e);
        }
    }

    public SteelSeriesAPI() {
        try (JsonReader reader = new JsonReader(new FileReader(core_properties_file))) {
            core_properties = new Gson().fromJson(reader, CoreProps.class);
        } catch (IOException e) {
            throw new RuntimeException("Could not read or parse coreProps.json", e);
        }
    }

    public CoreProps getCoreProperties() {
        return core_properties;
    }
}
