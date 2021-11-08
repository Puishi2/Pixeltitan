package de.vacebuild.api.commands;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    private VaceAPI plugin;

    public BuildCommand(VaceAPI plugin) {
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
                        if (plugin.isInBuildMode(player)) {
                            plugin.buildMode.remove(player);

                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt nicht mehr im §bBaumodus§8.");

                            player.setGameMode(GameMode.SURVIVAL);

                            plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bBaumodus", "§7Deaktiviert");
                        } else {
                            plugin.buildMode.add(player);

                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bBaumodus§8.");

                            player.setGameMode(GameMode.CREATIVE);

                            plugin.getTitleAPI().sendTitle(player, 5, 30, 5, "§8» §bBaumodus", "§7Aktiviert");
                        }
                        break;

                    case 1:
                        Player target = plugin.getServer().getPlayerExact(args[0]);

                        try {
                            if (plugin.isInBuildMode(target)) {
                                plugin.buildMode.remove(target);

                                target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt nicht mehr im §bBaumodus§8.");
                                player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt nicht mehr im §bBaumodus§8.");

                                player.setGameMode(GameMode.SURVIVAL);

                                plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bBaumodus", "§7Deaktiviert");
                            } else {
                                plugin.buildMode.add(target);

                                target.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt im §bBaumodus§8.");
                                player.sendMessage(plugin.getData().getPrefix() + "§b" + target.getName() + " §7ist jetzt im §bBaumodus§8.");

                                player.setGameMode(GameMode.CREATIVE);

                                plugin.getTitleAPI().sendTitle(target, 5, 30, 5, "§8» §bBaumodus", "§7Aktiviert");
                            }
                        } catch (NullPointerException e) {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7ist nicht online§8.");
                        }
                        break;

                    default:
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/build <Spieler>");
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
