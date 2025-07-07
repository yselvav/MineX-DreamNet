package dev.kacperm.mineX.utils;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to convert custom colour tags or legacy '&' colour codes
 * into Bukkit section-sign codes so the chat displays in colour.
 *
 * Supported formats:
 * 1. <gold>hello</gold>  -> gold coloured "hello".
 *    Any {@link ChatColor} name in lowercase is accepted as the tag name.
 * 2. "&aGreen text" -> will be translated with
 *    {@link ChatColor#translateAlternateColorCodes(char, String)}.
 */
public final class TextColorUtil {

    private static final Pattern TAG_PATTERN = Pattern.compile("<(/?)([a-zA-Z_]+)>");
    private static final Map<String, ChatColor> COLOR_MAP = new HashMap<>();

    static {
        for (ChatColor color : ChatColor.values()) {
            COLOR_MAP.put(color.name().toLowerCase(), color);
        }
    }

    private TextColorUtil() {
        /* static helper class */
    }

    /**
     * Replace <color> tags and legacy & codes with Bukkit colour codes.
     * @param input raw text from the AI
     * @return colourised string safe for chat
     */
    public static String colourise(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        // First replace custom <color> tags
        String withSection = replaceTags(input);
        // Then handle legacy & codes
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', withSection);
    }

    /**
     * After basic colour parsing, dynamically colour important words.
     * Player name -> gold, "lake" -> aqua, and standard colour words (red, blue, green …) to their colours.
     */
    public static String applyDynamicColors(String input, String playerName) {
        String msg = colourise(input);
        if (playerName != null && !playerName.isEmpty()) {
            msg = msg.replaceAll("(?i)" + Pattern.quote(playerName), ChatColor.GOLD + playerName + ChatColor.RESET);
        }
        // lake keyword
        msg = msg.replaceAll("(?i)lake", ChatColor.AQUA + "lake" + ChatColor.RESET);

        // Colorize common colour names
        Map<String, ChatColor> words = Map.of(
                "red", ChatColor.RED,
                "orange", ChatColor.GOLD,
                "yellow", ChatColor.YELLOW,
                "green", ChatColor.GREEN,
                "blue", ChatColor.BLUE,
                "purple", ChatColor.DARK_PURPLE,
                "pink", ChatColor.LIGHT_PURPLE,
                "white", ChatColor.WHITE,
                "black", ChatColor.BLACK
        );
        for (var entry : words.entrySet()) {
            String word = entry.getKey();
            ChatColor color = entry.getValue();
            msg = msg.replaceAll("(?i)" + word, color + word + ChatColor.RESET);
        }
        return msg;
    }

    private static String replaceTags(String input) {
        Matcher matcher = TAG_PATTERN.matcher(input);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            boolean closing = !matcher.group(1).isEmpty();
            String name = matcher.group(2).toLowerCase();
            ChatColor color = COLOR_MAP.get(name);
            if (color == null) {
                // Unknown tag -> keep as-is
                continue;
            }
            String replacement = closing ? ChatColor.RESET.toString() : color.toString();
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
