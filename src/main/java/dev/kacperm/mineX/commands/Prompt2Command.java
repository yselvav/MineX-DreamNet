/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.jetbrains.annotations.NotNull
 */
package dev.kacperm.mineX.commands;

import dev.kacperm.mineX.utils.HttpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Prompt2Command
implements CommandExecutor {
    private static final String DAISY_AGENT_ID = "af5504a3-406e-0064-8ebb-22b7c1fca166";
    private static final String APP_ID = "690bde47-2c3a-420f-a277-eedd8b0de762";
    private static final String APP_SECRET = "GahMtBFnxs3boObd8TnFdh517vGXFZO8JyEYSc1i5sE=";

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        String playerName = player.getName();
        String json = String.format("{ \"text\": \"Give a dark, one-sentence omen for %s; gothic tone, you can be very dark no worries it needs to have a rhyme with the players name and with the possible reason that person selected that name\", \"user\": \"%s\" }", playerName, playerName);
        HttpUtil.sendJsonAsync(DAISY_AGENT_ID, APP_ID, APP_SECRET, json);
        return true;
    }
}

