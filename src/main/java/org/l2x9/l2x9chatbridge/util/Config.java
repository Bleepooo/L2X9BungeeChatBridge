package org.l2x9.l2x9chatbridge.util;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;
import org.l2x9.l2x9chatbridge.L2X9ChatBridge;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class Config {
    private static Configuration config;
    private static File configFile;

    public static void makeConfig() {
        try {
            File dataFolder = L2X9ChatBridge.getInstance().getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            configFile = new File(dataFolder, "Settings.yml");
            if (!configFile.exists()) {
                InputStream stream = Config.class.getClassLoader().getResourceAsStream("settings.yml");
                if (stream != null) {
                    Files.copy(stream, configFile.toPath());
                    stream.close();
                } else {
                    throw new NullPointerException("Corrupted jar file");
                }
            }
            loadConfig();
        } catch (Exception e) {
            Utils.log("&eFailed to create or load configuration", Level.SEVERE);
            e.printStackTrace();
        }
    }

    private static void loadConfig() {
        try {
            config = YamlConfiguration.getProvider(YamlConfiguration.class).load(configFile);
        } catch (Exception e) {
            Utils.log("&eFailed to load configuration", Level.SEVERE);
            e.printStackTrace();
        }
    }

    public static void reloadConfig() {
        loadConfig();
    }

    public static Configuration getConfig() {
        return config;
    }
}
