package org.l2x9.l2x9chatbridge.listeners.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import org.jetbrains.annotations.NotNull;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

            if (event.getMessage().getContentRaw().equalsIgnoreCase(">online")) {
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("Online Players");
                embedBuilder.setColor(Color.CYAN);
                List<String> playerNames = new ArrayList<>();
                for (ProxiedPlayer player : Utils.getMainServer().getPlayers()) {
                    playerNames.add(player.getName());
                }
                String nameList = String.join("\n", playerNames);
                embedBuilder.setDescription(nameList + "\n\n" + playerNames.size() + " / 100");
                embedBuilder.setAuthor("L2X9 Bot", null, "https://images-ext-2.discordapp.net/external/-YlMK6cOc7nIR_OWWjY_ftndizbrXWNtVGsqHdHnSFM/%3Fsize%3D4096/https/cdn.discordapp.com/icons/847646170373292054/b148bff906be49793c581cc0eb541508.png");
                event.getChannel().sendMessage(embedBuilder.build()).queue();
            }
        }
    }
}
