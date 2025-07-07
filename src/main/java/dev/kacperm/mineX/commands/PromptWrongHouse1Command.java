package dev.kacperm.mineX.commands;

import dev.kacperm.mineX.utils.HttpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Command: /promptwronghouse1
 * Purpose: Tells the player (in a fun, light-hearted way) that they have taken
 *          a wrong turn and nudges them to look near a small lake.
 */
public class PromptWrongHouse1Command implements CommandExecutor {
    private static final String DAISY_AGENT_ID = "af5504a3-406e-0064-8ebb-22b7c1fca166";
    private static final String APP_ID = "690bde47-2c3a-420f-a277-eedd8b0de762";
    private static final String APP_SECRET = "GahMtBFnxs3boObd8TnFdh517vGXFZO8JyEYSc1i5sE=";

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                              @NotNull Command command,
                              @NotNull String label,
                              @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        String playerName = player.getName();

        // Compose a playful, rhyming prompt referencing the player's name and pointing them near the lake.
        String promptText = String.format("Compose a witty, rhyming quip for adventurer %s who has barged into the wrong house. " +
                "Tease them nicely about their dazzling sense of direction. The verse should rhyme with the players name at least once " +
                "and end by hinting that the correct path lies near a small lake Keep it upbeat and humorous. imporant you mention them to check near the late", playerName);

        String json = HttpUtil.buildJson(promptText, playerName);
        HttpUtil.sendJsonAsync(DAISY_AGENT_ID, APP_ID, APP_SECRET, json, playerName);
        return true;
    }
}
