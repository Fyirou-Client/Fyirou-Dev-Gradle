package xyz.fyirou.Gradle.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import xyz.fyirou.Gradle.extension.FyirouExtension;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LibrariesUtil {
    public static String getJson(String version) {
        String jsonUrl = "";
        for (JsonElement versions : new Gson().fromJson(UrlUtil.readString(UrlUtil.GAME_URL), JsonObject.class).get("versions").getAsJsonArray()) {
            if (versions.getAsJsonObject().get("id").getAsString().equals(version)) {
                jsonUrl = versions.getAsJsonObject().get("url").getAsString();
            }
        }
        return UrlUtil.readString(jsonUrl);
    }

    public static List<String> getLibraries(FyirouExtension extension) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        for (JsonElement jsonElement : new Gson().fromJson(getJson(extension.version), JsonObject.class).get("libraries").getAsJsonArray()) {
            if (jsonElement.getAsJsonObject().has("natives")) {
                continue;
            }

            String name = jsonElement.getAsJsonObject().get("name").getAsString();
            linkedHashMap.put(name.substring(0, name.lastIndexOf(":")), name.substring(name.lastIndexOf(":")));
        }
        List<String> libraries = new ArrayList<>();
        for (Map.Entry<String, String> stringStringEntry : linkedHashMap.entrySet()) {
            libraries.add(stringStringEntry.getKey() + stringStringEntry.getValue());
        }
        return libraries;
    }
}
