package org.l2x9.l2x9chatbridge.util;

import java.util.List;

public class Constants {
    public static final String TOKEN = Config.getConfig().getString("token");
    public static final String COMMAND_PREFIX = Config.getConfig().getString("CommandPrefix");
    public static final String CHANNEL_ID = Config.getConfig().getString("ChannelID");
    public static final String WATCHING = Config.getConfig().getString("Watching");
    public static final String MAIN_SERVER_NAME = Config.getConfig().getString("MainServerName");
    public static final String PLAYER_JOIN = Config.getConfig().getString("PlayerJoin");
    public static final String PLAYER_LEAVE = Config.getConfig().getString("PlayerLeave");
    public static final String PLAYER_CHAT = Config.getConfig().getString("PlayerChat");
    public static final boolean ENABLE_DEATH_MESSAGES = Config.getConfig().getBoolean("Enable-Death-Messages");
    public static final long MESSAGE_SEND_DELAY = Config.getConfig().getLong("MessageSendDelay");
    public static final List<String> BLOCKED_WORDS = Config.getConfig().getStringList("BlockedWords");
    public static final String PREFIX = "[&3&lL2X9&r&b&lChatBridge&r] ";
    public static final String DISCORD_FORMAT = Config.getConfig().getString("DiscordFormat");


}
