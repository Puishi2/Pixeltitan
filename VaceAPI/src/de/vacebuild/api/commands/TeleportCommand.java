package de.vacebuild.api.commands;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

    private VaceAPI plugin;

    public TeleportCommand(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("pixel.use")) {
                switch (args.length) {
                    case 1:
                        if (args[0].equalsIgnoreCase("@a")) {
                            if (player.hasPermission("vacebuild.admin")) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Alle Spieler wurden zu §bdir §7teleportiert§8.");

                                for (Player all : plugin.getServer().getOnlinePlayers()) {
                                    all.teleport(player.getLocation());
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                            }
                        } else {
                            Player target = plugin.getServer().getPlayerExact(args[0]);

                            try {
                                if (!plugin.isInVanishMode(target) || player.hasPermission("vacebuild.admin")) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Du wurdest zu §b" + target.getName() +
                                            " §7teleportiert§8.");

                                    player.teleport(target.getLocation());
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                }
                            } catch (NullPointerException e) {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                            }
                        }
                        break;

                    case 2:
                        if (args[0].equalsIgnoreCase("@a")) {
                            if (player.hasPermission("vacebuild.admin")) {
                                Player player1 = plugin.getServer().getPlayerExact(args[1]);

                                try {
                                    if (!plugin.isInVanishMode(player1) || player.hasPermission("vacebuild.admin")) {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Alle Spieler wurden zu §b"
                                                + player1.getName() + " §7teleportiert§8.");

                                        for (Player all : plugin.getServer().getOnlinePlayers()) {
                                            all.teleport(player1.getLocation());
                                        }
                                    } else {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                    }
                                } catch (NullPointerException e) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                            }
                        } else {
                            if (player.hasPermission("vacebuild.admin")) {
                                Player player1 = plugin.getServer().getPlayerExact(args[0]);
                                Player player2 = plugin.getServer().getPlayerExact(args[1]);

                                try {
                                    if (!plugin.isInVanishMode(player1) && !plugin.isInVanishMode(player2) || player.hasPermission("vacebuild.admin")) {
                                        player.sendMessage(plugin.getData().getPrefix() + "§b" + player1.getName() +
                                                " §7wurde zu §b" + player2.getName() + " §7teleportiert§8.");

                                        player1.teleport(player2.getLocation());
                                    } else {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                    }
                                } catch (NullPointerException e) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                            }
                        }
                        break;

                    case 3:
                        if (plugin.isOnlyNumbers(args[0]) && plugin.isOnlyNumbers(args[1]) && plugin.isOnlyNumbers(args[2])) {
                            int x = Integer.valueOf(args[0]);
                            int y = Integer.valueOf(args[1]);
                            int z = Integer.valueOf(args[2]);

                            player.sendMessage(plugin.getData().getPrefix() + "§7Du wurdest zu §b" + x + " " + y + " " + z +
                                    " §7teleportiert§8.");

                            player.teleport(new Location(player.getWorld(), (double) x, (double) y, (double) z));
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib nur §bgültige Zahlen §7an§8.");
                        }

                        break;

                    case 4:
                        if (player.hasPermission("vacebuild.admin")) {
                            if (args[0].equalsIgnoreCase("@a")) {
                                if (plugin.isOnlyNumbers(args[1]) && plugin.isOnlyNumbers(args[2]) && plugin.isOnlyNumbers(args[3])) {
                                    int x = Integer.valueOf(args[1]);
                                    int y = Integer.valueOf(args[2]);
                                    int z = Integer.valueOf(args[3]);

                                    player.sendMessage(plugin.getData().getPrefix() + "§7Alle Spieler " +
                                            " §7wurden zu §b" + x + " " + y + " " + z + " §7teleportiert§8.");

                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        all.teleport(new Location(player.getWorld(), (double) x, (double) y, (double) z));
                                    }
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib nur §bgültige Zahlen §7an§8.");
                                }
                            } else {
                                Player target = plugin.getServer().getPlayerExact(args[0]);

                                try {
                                    if (!plugin.isInVanishMode(target) || player.hasPermission("vacebuild.admin")) {
                                        if (plugin.isOnlyNumbers(args[1]) && plugin.isOnlyNumbers(args[2]) && plugin.isOnlyNumbers(args[3])) {
                                            int x = Integer.valueOf(args[1]);
                                            int y = Integer.valueOf(args[2]);
                                            int z = Integer.valueOf(args[3]);

                                            player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() +
                                                    " §7wurde zu §b" + x + " " + y + " " + z + " §7teleportiert§8.");

                                            target.teleport(new Location(player.getWorld(), (double) x, (double) y, (double) z));
                                        } else {
                                            player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib nur §bgültige Zahlen §7an§8.");
                                        }
                                    } else {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                    }
                                } catch (NullPointerException e) {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                                }
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dafür hast du keine §bRechte§8.");
                        }
                        break;

                    default:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/teleport <Spieler §8- §bKoordinaten>");
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
