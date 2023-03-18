package xyz.fyirou.Gradle.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class UrlUtil {
    public static final String GAME_URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";
    public static final String GAME_LIBRARIES = "https://libraries.minecraft.net";

    public static String readString(String link) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            URL url = new URL(link);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = ((HttpURLConnection) urlConnection);
            }
            if (connection != null) {
                IOUtils.readLines(connection.getInputStream(), StandardCharsets.UTF_8).forEach(line -> stringBuilder.append(line).append("\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
