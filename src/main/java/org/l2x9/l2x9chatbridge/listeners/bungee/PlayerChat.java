package org.l2x9.l2x9chatbridge.listeners.bungee;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;

public class PlayerChat implements Listener {
    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (Utils.isOnMainServer(player)) {
                if (!Utils.hasBlockedWord(event.getMessage())) {
                    if (!event.getMessage().startsWith("/")) {
                        String message = Constants.PLAYER_CHAT
                                .replace("$player$", player.getName())
                                .replace("$message$", event.getMessage()
                                );
                        L2X9ChatBridge.getInstance().getMessageQueue().add(message);
                    }
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}
