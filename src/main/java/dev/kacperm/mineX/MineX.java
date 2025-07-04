/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package dev.kacperm.mineX;

import dev.kacperm.mineX.commands.Prompt1Command;
import dev.kacperm.mineX.commands.Prompt2Command;
import dev.kacperm.mineX.commands.Prompt3Command;
import dev.kacperm.mineX.commands.Prompt4Command;
import dev.kacperm.mineX.commands.Prompt5Command;
import dev.kacperm.mineX.commands.PromptBlueCommand;
import dev.kacperm.mineX.commands.PromptYellowCommand;
import dev.kacperm.mineX.commands.PromptOrangeCommand;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineX
extends JavaPlugin {
    private static MineX instance;
    private static final String DAISY_AGENT_ID = "af5504a3-406e-0064-8ebb-22b7c1fca166";
    private static final String APP_ID = "690bde47-2c3a-420f-a277-eedd8b0de762";
    private static final String APP_SECRET = "GahMtBFnxs3boObd8TnFdh517vGXFZO8JyEYSc1i5sE=";

    public void onEnable() {
        instance = this;
        this.deleteMemories(DAISY_AGENT_ID, APP_ID, APP_SECRET);
        Objects.requireNonNull(this.getCommand("prompt1")).setExecutor((CommandExecutor)new Prompt1Command());
        Objects.requireNonNull(this.getCommand("prompt2")).setExecutor((CommandExecutor)new Prompt2Command());
        Objects.requireNonNull(this.getCommand("prompt3")).setExecutor((CommandExecutor)new Prompt3Command());
        Objects.requireNonNull(this.getCommand("prompt4")).setExecutor((CommandExecutor)new Prompt4Command());
        Objects.requireNonNull(this.getCommand("prompt5")).setExecutor((CommandExecutor)new Prompt5Command());
        Objects.requireNonNull(this.getCommand("promptblue")).setExecutor((CommandExecutor)new PromptBlueCommand());
        Objects.requireNonNull(this.getCommand("promptyellow")).setExecutor((CommandExecutor)new PromptYellowCommand());
        Objects.requireNonNull(this.getCommand("promptorange")).setExecutor((CommandExecutor)new PromptOrangeCommand());
    }

    public void onDisable() {
        instance = null;
    }

    public static MineX getInstance() {
        return instance;
    }

    public void deleteMemories(String agentId, String appId, String appSecret) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this, () -> {
            try {
                String urlStr = "https://agents-api.doodles.app/" + agentId + "/memories";
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("DELETE");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("x-mini-app-id", appId);
                conn.setRequestProperty("x-mini-app-secret", appSecret);
                int responseCode = conn.getResponseCode();
                if (responseCode == 200 || responseCode == 204) {
                    Bukkit.getConsoleSender().sendMessage("[Daisy] Memories deleted successfully.");
                } else {
                    Bukkit.getLogger().warning("[Daisy] Failed to delete memories. Code: " + responseCode);
                }
                conn.disconnect();
            }
            catch (Exception e) {
                Bukkit.getLogger().warning("\u00a7c[Daisy] Error deleting memories: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}

