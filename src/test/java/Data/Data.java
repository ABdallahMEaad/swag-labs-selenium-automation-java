package Data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Data {

    private static final String TEST_DATA_FILE = "src/test/resources/testdata.json";

    @DataProvider(name = "credentials")
    public Object[][] getCredentials() {
        return readRows("credentials", "username", "password");
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        return readRows("invalidLoginData", "username", "password");
    }

    @DataProvider(name = "invalidLoginDataWitherrorMessages")
    public Object[][] getInvalidLoginDataWithErrorMessages() {
        return readRows("invalidLoginDataWitherrorMessages", "username", "password", "errorMessage");
    }

    @DataProvider(name = "productsData")
    public Object[][] getProducts() {
        JsonArray array = readArray("productsData");
        Object[][] rows = new Object[array.size()][1];

        for (int i = 0; i < array.size(); i++) {
            List<String> products = new ArrayList<>();
            for (JsonElement product : array.get(i).getAsJsonArray()) {
                products.add(product.getAsString());
            }
            rows[i][0] = products;
        }
        return rows;
    }

    @DataProvider(name = "deliveryData")
    public Object[][] getDeliveryData() {
        return readRows("deliveryData", "firstName", "lastName", "zip");
    }

    private static Object[][] readRows(String dataName, String... keys) {
        JsonArray array = readArray(dataName);
        Object[][] rows = new Object[array.size()][keys.length];

        for (int i = 0; i < array.size(); i++) {
            JsonObject row = array.get(i).getAsJsonObject();
            for (int j = 0; j < keys.length; j++) {
                rows[i][j] = row.get(keys[j]).getAsString();
            }
        }
        return rows;
    }

    private static JsonArray readArray(String dataName) {
        try (Reader reader = Files.newBufferedReader(Path.of(TEST_DATA_FILE), StandardCharsets.UTF_8)) {
            JsonArray array = JsonParser.parseReader(reader).getAsJsonObject().getAsJsonArray(dataName);
            if (array == null) {
                throw new IllegalArgumentException("'" + dataName + "' was not found in " + TEST_DATA_FILE);
            }
            return array;
        } catch (java.io.IOException e) {
            throw new RuntimeException("Could not read test data file: " + TEST_DATA_FILE, e);
        }
    }
}
