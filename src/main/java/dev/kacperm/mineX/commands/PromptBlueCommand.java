package dev.kacperm.mineX.commands;

import dev.kacperm.mineX.utils.HttpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PromptBlueCommand implements CommandExecutor {
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
        String json = String.format("{ \"text\": \"The player %s has chosen the color blue as their name. Write an extremely positive, thoughtful, and creative analysis about why someone would choose blue for their name. Please deeply investigate and hypothesize what blue could mean to this player, and what it might represent about their personality, feelings, or aspirations. Make it clear that you, as an AI, are curious and want to connect the player's name to the color blue in a meaningful way.\", \"user\": \"%s\" }", playerName, playerName);
        HttpUtil.sendJsonAsync(DAISY_AGENT_ID, APP_ID, APP_SECRET, json);
        return true;
    }
}
