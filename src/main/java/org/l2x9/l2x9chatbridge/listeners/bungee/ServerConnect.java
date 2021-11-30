package org.l2x9.l2x9chatbridge.listeners.bungee;

import net.dv8tion.jda.api.EmbedBuilder;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;
import org.l2x9.l2x9chatbridge.workers.MessageWorker;

import java.awt.*;
import java.util.Queue;

public class ServerConnect implements Listener {
    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        if (event.getTarget() == Utils.getMainServer()) {
            String name = event.getPlayer().getName();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(name + " joined");
            embedBuilder.setColor(Color.CYAN);
            MessageWorker.sendEmbed(embedBuilder.build());
        }
    }
}
