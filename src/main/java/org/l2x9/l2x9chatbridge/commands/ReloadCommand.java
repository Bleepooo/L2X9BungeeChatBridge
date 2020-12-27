package org.l2x9.l2x9chatbridge.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import org.l2x9.l2x9chatbridge.util.Config;
import org.l2x9.l2x9chatbridge.util.Constants;
import org.l2x9.l2x9chatbridge.util.Utils;

public class ReloadCommand extends Command {
    public ReloadCommand() {
        super("chatbridge");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("chatbridge.admin")) {
            switch (args[0]) {
                case "reload":
                    Utils.sendMessage(sender, Constants.PREFIX + "&aReloading configuration");
                    Config.reloadConfig();
                    break;
                default:
                    Utils.sendMessage(sender, Constants.PREFIX + "&4Error:&r&c Unknown sub command");
            }
        }
    }
}
