package dev.kacperm.mineX.commands;

import dev.kacperm.mineX.utils.HttpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Command: /promptwronghouse2
 * Purpose: Gives a stern, sarcastic remark to the player for taking the wrong route
 *          and firmly redirects them toward the small lake.
 */
public class PromptWrongHouse2Command implements CommandExecutor {
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

        // Compose a sarcastic, ironic reprimand that pokes fun at the player and points them near the lake.
        String promptText = String.format("In a dry, sarcastic tone, chastise %s for their astounding navigational prowess in choosing the *wrong* house. " +
                "Mock their decision-making skills, imply their compass might be upside-down, and finish by bluntly instructing them to look near the *small lake*. Keep it witty but not mean-spirited. is very important to tell the player to check the small lake", playerName);

        String json = HttpUtil.buildJson(promptText, playerName);
        HttpUtil.sendJsonAsync(DAISY_AGENT_ID, APP_ID, APP_SECRET, json, playerName);
        return true;
    }
}
