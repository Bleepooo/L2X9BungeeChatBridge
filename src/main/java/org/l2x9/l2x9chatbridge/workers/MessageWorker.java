package org.l2x9.l2x9chatbridge.workers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.EmbedType;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;

import java.util.Queue;

public class MessageWorker {
    public static void sendEmbed(MessageEmbed embed) {
        TextChannel channel = L2X9ChatBridge.getInstance().getChannel();
        channel.sendMessage(embed).queue();
    }
}
