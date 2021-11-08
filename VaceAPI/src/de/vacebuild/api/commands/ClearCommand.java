package de.vacebuild.api.commands;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCommand implements CommandExecutor {

    private VaceAPI plugin;

    public ClearCommand(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("pixel.use")) {
                switch (args.length) {
                    case 0:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Dein §bInventar §7wurde geleert§8.");

                        player.getInventory().clear();
                        break;

                    case 1:
                        if (player.hasPermission("vacebuild.admin")) {
                            Player target = plugin.getServer().getPlayerExact(args[0]);

                            try {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Das Inventar von §b" + target.getName() + " §7wurde geleert§8.");
                                target.sendMessage(plugin.getData().getPrefix() + "§7Dein §bInventar §7wurde geleert§8.");

                                target.getInventory().clear();
                            } catch (NullPointerException e) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                        }
                        break;

                    default:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/clear <Spieler>");
                        break;
                }
            } else {
                plugin.getData().noPerms(player);
            }
        } else {
            plugin.getData().noPlayer();
        }

        return false;
    }

}
