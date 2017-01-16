package bsr.server;

import bsr.server.model.Config;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Maciej on 2017-01-16.
 */
public class Utils {

    public static Config getConfig(){
        try {
            JsonElement jsonElement = new JsonParser().parse(new FileReader("config.json"));
            return new Gson().fromJson(jsonElement, Config.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
