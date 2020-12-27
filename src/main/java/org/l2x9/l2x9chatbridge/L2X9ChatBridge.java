package org.l2x9.l2x9chatbridge;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.l2x9.l2x9chatbridge.commands.ReloadCommand;
import org.l2x9.l2x9chatbridge.listeners.bungee.PlayerChat;
import org.l2x9.l2x9chatbridge.listeners.bungee.ServerConnect;
import org.l2x9.l2x9chatbridge.listeners.bungee.ServerDisconnect;
import org.l2x9.l2x9chatbridge.listeners.discord.MessageSendEvent;
import org.l2x9.l2x9chatbridge.util.Config;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;
import org.l2x9.l2x9chatbridge.workers.MessageWorker;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class L2X9ChatBridge extends Plugin {
    private static L2X9ChatBridge INSTANCE;
    private final Queue<String> MESSAGE_QUEUE = new LinkedList<>();
    private final Object[] DISCORD_LISTENERS = {new MessageSendEvent()};
    private final PluginManager PLUGIN_MANAGER = getProxy().getPluginManager();
    ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
    private JDA jda;
    private TextChannel channel;

    public static L2X9ChatBridge getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        //Bungee stuff
        if (INSTANCE == null) {
            INSTANCE = this;
        }
        Config.makeConfig();
        PLUGIN_MANAGER.registerListener(this, new PlayerChat());
        PLUGIN_MANAGER.registerListener(this, new ServerConnect());
        PLUGIN_MANAGER.registerListener(this, new ServerDisconnect());
        PLUGIN_MANAGER.registerCommand(this, new ReloadCommand());
        //Discord bot stuff
        try {
            JDABuilder jdaBuilder = JDABuilder.createDefault(Constants.TOKEN);
            jdaBuilder.addEventListeners(DISCORD_LISTENERS);
            jdaBuilder.setActivity(Activity.watching(Constants.WATCHING));
            jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            jda = jdaBuilder.build();
            jda.awaitReady();
        } catch (Exception e) {
            Utils.log("&eFailed to start bot");
            e.printStackTrace();
        }
        channel = jda.getTextChannelById(Constants.CHANNEL_ID);
        //General stuff
        service.scheduleAtFixedRate(MessageWorker::sendMessage, 0, Constants.MESSAGE_SEND_DELAY, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Queue<String> getMessageQueue() {
        return MESSAGE_QUEUE;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public JDA getJda() {
        return jda;
    }
}
