package org.l2x9.l2x9chatbridge.listeners.bungee;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;
import org.l2x9.l2x9chatbridge.workers.MessageWorker;

import java.awt.*;

public class PlayerChat implements Listener {
    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.getSender() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            if (Utils.isOnMainServer(player)) {
                if (!Utils.hasBlockedWord(event.getMessage())) {
                    if (!event.getMessage().startsWith("/") && !event.getMessage().startsWith(">")) {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setDescription("<" + player.getName() + "> " + event.getMessage());
                        embedBuilder.setColor(Color.GRAY);
                        MessageWorker.sendEmbed(embedBuilder.build());
                    }

                    if (event.getMessage().startsWith(">")) {
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setDescription("<" + player.getName() + "> " + event.getMessage());
                        embedBuilder.setColor(Color.GREEN);
                        MessageWorker.sendEmbed(embedBuilder.build());
                    }
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}
