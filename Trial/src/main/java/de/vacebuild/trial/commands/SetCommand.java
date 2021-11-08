package de.vacebuild.trial.commands;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.api.utils.ArmorStandAPI;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {

    private Trialsystem plugin;

    public SetCommand(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("vacebuild.admin")) {
                if (args.length == 0) {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/set <Spawn §8- §bAST>");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("spawn")) {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast den §bSpawn §7gesetzt§8.");

                        VaceAPI.getApi().getLocationAPI().addLocation(player.getLocation(), "Spawn.Spawn");
                    } else if (args[0].equalsIgnoreCase("ast")) {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast den §bArmorStand §7gesetzt§8.");

                        new ArmorStandAPI(player.getLocation(), "§8» §bWorldmanager")
                                .setHelmet("http://textures.minecraft.net/texture/7fc0a30139f22b4a714197d5e017fb1d6ad02f1f43c09df0f6af68a63e6c2")
                                .setChestplate(Color.AQUA).setLeggings(Color.GRAY).setBoots(Color.BLACK).spawn();
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/set <Spawn §8- §bAST>");
                    }
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/set <Spawn §8- §bAST>");
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
