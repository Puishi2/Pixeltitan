package de.vacebuild.api.commands;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.vacebuild.api.main.VaceAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private VaceAPI plugin;

    public VanishCommand(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("pixel.admin")) {
                switch (args.length) {
                    case 0:
                        if (plugin.isInVanishMode(player)) {
                            plugin.vanishMode.remove(player);

                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt nicht mehr im §bVanish Modus§8.");

                            for (Player all : plugin.getServer().getOnlinePlayers()) {
                                all.showPlayer(player);
                            }

                            plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bVanish", "§7Deaktiviert");
                        } else {
                            plugin.vanishMode.add(player);

                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bVanish Modus§8.");

                            for (Player all : plugin.getServer().getOnlinePlayers()) {
                                if (!all.hasPermission("vacebuild.admin")) {
                                    all.hidePlayer(player);
                                }
                            }

                            plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bVanish", "§7Aktiviert");

                            if (!CloudServer.getInstance().getServerProcessMeta().getServiceId().getGroup().equalsIgnoreCase("Lobby")) {
                                for (Player all : plugin.getServer().getOnlinePlayers()) {
                                    if (!all.hasPermission("vacebuild.admin")) {
                                        all.sendMessage("§8« §b" + player.getName() + " §7hat den Server verlassen§8.");
                                    } else {
                                        all.sendMessage("§8« §b" + player.getName() + " §7hat den Server verlassen §8(§7Vanish§8)");
                                    }
                                }
                            }
                        }
                        break;

                    case 1:
                        Player target = plugin.getServer().getPlayerExact(args[0]);

                        try {
                            if (plugin.isInVanishMode(target)) {
                                plugin.vanishMode.remove(target);

                                target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt nicht mehr im §bVanish Modus§8.");
                                player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt nicht mehr im §bVanish Modus§8.");

                                for (Player all : plugin.getServer().getOnlinePlayers()) {
                                    all.showPlayer(target);
                                }

                                plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bVanish", "§7Deaktiviert");
                            } else {
                                plugin.vanishMode.add(target);

                                target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bVanish Modus§8.");
                                player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bVanish Modus§8.");

                                for (Player all : plugin.getServer().getOnlinePlayers()) {
                                    if (!all.hasPermission("vacebuild.admin")) {
                                        all.hidePlayer(target);
                                    }
                                }

                                plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bVanish", "§7Aktiviert");

                                if (!CloudServer.getInstance().getServerProcessMeta().getServiceId().getGroup().equalsIgnoreCase("Lobby")) {
                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        if (!all.hasPermission("vacebuild.admin")) {
                                            all.sendMessage("§8« §b" + target.getName() + " §7hat den Server verlassen§8.");
                                        } else {
                                            all.sendMessage("§8« §b" + target.getName() + " §7hat den Server verlassen §8(§7Vanish§8)");
                                        }
                                    }
                                }
                            }
                        } catch (NullPointerException e) {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                        }
                        break;

                    default:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/vanish <Spieler>");
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
