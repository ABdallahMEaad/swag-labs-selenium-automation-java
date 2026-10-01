package Utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigHandler {
    JsonObject config;

    public ConfigHandler(String filepath) {

        try (Reader reader = Files.newBufferedReader(Path.of(filepath), StandardCharsets.UTF_8)) {
            config = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Could not read config file: " + filepath, e);
        }

    }

    public String getValue(String Key) {
        JsonElement value = config.get(Key);
        return value == null ? null : value.getAsString();
    }
}
