package org.l2x9.l2x9chatbridge.listeners.discord;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;

public class MessageSendEvent extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            if (event.getChannel().equals(L2X9ChatBridge.getInstance().getChannel())) {
                String tag = event.getAuthor().getAsTag();
                String message = ChatColor.stripColor(event.getMessage().getContentRaw());
                String formatted = Constants.DISCORD_FORMAT
                        .replace("$tag$", tag)
                        .replace("$message$", message
                        );
                for (ProxiedPlayer player : Utils.getMainServer().getPlayers()) {
                    Utils.sendMessage(player, formatted);
                }
                Utils.log(formatted);
            }
        }
    }
}
