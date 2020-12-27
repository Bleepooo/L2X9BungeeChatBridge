package org.l2x9.l2x9chatbridge.listeners.bungee;

import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;

import java.util.Queue;

public class ServerConnect implements Listener {
    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        if (event.getTarget() == Utils.getMainServer()) {
            String name = event.getPlayer().getName();
            Queue<String> messageQueue = L2X9ChatBridge.getInstance().getMessageQueue();
            String message = Constants.PLAYER_JOIN
                    .replace("$player$", name);
            messageQueue.add(message);
        }
    }
}
