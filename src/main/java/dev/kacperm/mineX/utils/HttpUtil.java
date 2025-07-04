/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package dev.kacperm.mineX.utils;

import dev.kacperm.mineX.MineX;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {
    public static void sendJsonAsync(final String agentId, final String appId, final String appSecret, final String json) {
        new BukkitRunnable(){

            public void run() {
                try {
                    String urlStr = "https://agents-api.doodles.app/" + agentId + "/user/message";
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("x-mini-app-id", appId);
                    conn.setRequestProperty("x-mini-app-secret", appSecret);
                    Bukkit.getConsoleSender().sendMessage("Sending JSON: " + json);
                    try (OutputStream os = conn.getOutputStream();){
                        byte[] input = json.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    int responseCode = conn.getResponseCode();
                    InputStream responseStream = responseCode >= 200 && responseCode < 400 ? conn.getInputStream() : conn.getErrorStream();
                    StringBuilder responseBody = new StringBuilder();
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, "utf-8"));){
                        String line;
                        while ((line = reader.readLine()) != null) {
                            responseBody.append(line.trim());
                        }
                    }
                    if (responseCode != 202 && responseCode != 200) {
                        Bukkit.getLogger().warning("Daisy API rejected the request. Response code: " + responseCode);
                        Bukkit.getLogger().warning("Response body: " + responseBody.toString());
                    } else {
                        Bukkit.getConsoleSender().sendMessage("JSON sent successfully. CODE " + responseCode);
                        try {
                            JSONArray responseArray = new JSONArray(responseBody.toString());
                            for (int i = 0; i < responseArray.length(); ++i) {
                                JSONObject obj = responseArray.getJSONObject(i);
                                String text = obj.optString("text", "");
                                Bukkit.broadcastMessage((String)("Deysi: " + text));
                            }
                        }
                        catch (JSONException e) {
                            Bukkit.getLogger().warning("Failed to parse response JSON: " + e.getMessage());
                        }
                    }
                    conn.disconnect();
                    try {
                        HttpUtil.deleteMemories(agentId, appId, appSecret);
                    } catch (IOException ioException) {
                        Bukkit.getLogger().warning("Could not wipe memories for agent: " + ioException.getMessage());
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously((Plugin)MineX.getInstance());
    }

    public static void deleteMemories(String agentId, String appId, String appSecret) throws IOException {
        String url = "https://agents-api.doodles.app/agents/" + agentId + "/memories";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("x-mini-app-id", appId);
        conn.setRequestProperty("x-mini-app-secret", appSecret);
        conn.getResponseCode();
    }
}
