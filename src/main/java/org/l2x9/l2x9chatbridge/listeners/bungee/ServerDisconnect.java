package org.l2x9.l2x9chatbridge.listeners.bungee;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;
import org.l2x9.l2x9chatbridge.workers.MessageWorker;

import java.awt.*;
import java.util.Queue;

public class ServerDisconnect implements Listener {
    @EventHandler
    public void onDisconnect(ServerDisconnectEvent event) {
        ServerInfo disconnected = event.getTarget();
        if (disconnected == Utils.getMainServer()) {
            String name = event.getPlayer().getName();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(name + " left");
            embedBuilder.setColor(Color.RED);
            MessageWorker.sendEmbed(embedBuilder.build());
        }
    }
}