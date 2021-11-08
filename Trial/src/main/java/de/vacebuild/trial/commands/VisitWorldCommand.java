package de.vacebuild.trial.commands;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class VisitWorldCommand implements CommandExecutor {

    private Trialsystem plugin;

    public VisitWorldCommand(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            if (player.hasPermission("vacebuild.admin")) {
                if (args.length == 0) {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/visitworld <Spieler>");
                } else if (args.length == 1) {
                    String name = args[0];

                    if (VaceAPI.getApi().getPlayerAPI().isPlayerExists(name)) {
                        UUID uuid = VaceAPI.getApi().getPlayerAPI().getUUID(name);
                        name = VaceAPI.getApi().getPlayerAPI().getName(uuid);

                        if (new File("Worlds//" + uuid.toString()).exists()) {
                            World world = plugin.getServer().getWorld("Worlds//" + uuid.toString());

                            if (world != null) {
                                player.teleport(world.getSpawnLocation());
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt auf der Welt von §b" + name + "§8.");
                            } else {
                                plugin.getServer().createWorld(new WorldCreator("Worlds//" + uuid.toString()));

                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist jetzt auf der Welt von §b" + name + "§8.");

                                plugin.getServer().getScheduler().runTaskLater(plugin, () -> player.teleport(plugin.getServer().
                                        getWorld("Worlds//" + uuid.toString()).getSpawnLocation()), 5L);
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7hat noch keine Welt§8.");
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Dieser §bSpieler §7war noch nie auf dem Server§8.");
                    }
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Verwende §b/visitworld <Spieler>");
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
