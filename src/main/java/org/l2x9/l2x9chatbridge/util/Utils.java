package org.l2x9.l2x9chatbridge.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private static final Logger LOGGER = L2X9ChatBridge.getInstance().getLogger();
    private static final ProxyServer waterfall = ProxyServer.getInstance();

    public static void log(String message) {
        LOGGER.log(Level.INFO, ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void log(String message, Level level) {
        LOGGER.log(level, ChatColor.translateAlternateColorCodes('&', message));
    }

    public static ServerInfo getMainServer() {
        return waterfall.getServerInfo(Constants.MAIN_SERVER_NAME);
    }

    public static boolean isOnMainServer(ProxiedPlayer player) {
        return getMainServer().getPlayers().contains(player);
    }

    public static boolean hasBlockedWord(String message) {
        boolean hasBlockedWord = false;
        for (String word : message.split(" ")) {
            if (Constants.BLOCKED_WORDS.contains(word)) {
                hasBlockedWord = true;
                break;
            }
        }
        return hasBlockedWord;
    }

    public static void sendMessage(CommandSender sender, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        TextComponent component = new TextComponent(message);
        sender.sendMessage(component);
    }

    public static void sendMessage(ProxiedPlayer player, String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        TextComponent component = new TextComponent(message);
        player.sendMessage(component);
    }
}
