package bsr.server;

import bsr.server.model.Config;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Maciej on 2017-01-16.
 */
public class Utils {

    private static final String DATA_JSON = "data.json";
    private static final String CONFIG_JSON = "config.json";

    public static Config getConfig() {
        try {
            JsonElement jsonElement = new JsonParser().parse(new FileReader(CONFIG_JSON));
            return new Gson().fromJson(jsonElement, Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void save(Data data) {
        try (FileWriter file = new FileWriter(DATA_JSON)) {
            String json = new Gson().toJson(data);
            file.write(json);
            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Data getData() {
        try {
            JsonElement jsonElement = new JsonParser().parse(new FileReader(DATA_JSON));
            return new Gson().fromJson(jsonElement, Data.class);
        } catch (FileNotFoundException e) {
            return new Data();
        }
    }
}
