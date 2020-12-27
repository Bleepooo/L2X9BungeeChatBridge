package org.l2x9.l2x9chatbridge.workers;

import net.dv8tion.jda.api.entities.TextChannel;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;

import java.util.Queue;

public class MessageWorker {
    public static void sendMessage() {
        Queue<String> messageQueue = L2X9ChatBridge.getInstance().getMessageQueue();
        String poll = messageQueue.poll();
        TextChannel channel = L2X9ChatBridge.getInstance().getChannel();
        if (poll != null) {
            channel.sendMessage(poll).queue();
        }
    }
}
