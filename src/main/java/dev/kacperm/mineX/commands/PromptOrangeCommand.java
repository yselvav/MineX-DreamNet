package dev.kacperm.mineX.commands;

import dev.kacperm.mineX.utils.HttpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PromptOrangeCommand implements CommandExecutor {
    private static final String DAISY_AGENT_ID = "af5504a3-406e-0064-8ebb-22b7c1fca166";
    private static final String APP_ID = "690bde47-2c3a-420f-a277-eedd8b0de762";
    private static final String APP_SECRET = "GahMtBFnxs3boObd8TnFdh517vGXFZO8JyEYSc1i5sE=";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        String playerName = player.getName();
        String json = String.format("{ \"text\": \"The player %s has chosen the color orange as their name. Write a highly critical, witty, and negative analysis about why someone would choose orange for their name. Be sure to judge and speculate about the player's choice, and make it clear you, as an AI, are deeply curious and want to connect their name to the color orange in a meaningful way. Try to investigate and theorize what orange might represent about this player and why they would make such a peculiar choice.\", \"user\": \"%s\" }", playerName, playerName);
        HttpUtil.sendJsonAsync(DAISY_AGENT_ID, APP_ID, APP_SECRET, json);
        return true;
    }
}
