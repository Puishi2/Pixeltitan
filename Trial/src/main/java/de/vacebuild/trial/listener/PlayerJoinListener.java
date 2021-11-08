package de.vacebuild.trial.listener;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Trialsystem plugin;

    public PlayerJoinListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        player.setGameMode(GameMode.CREATIVE);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.setHealthScale(20);
        player.setExp(0);
        player.setLevel(0);

        if (VaceAPI.getApi().getLocationAPI().existsLocation("Spawn.Spawn")) {
            player.teleport(VaceAPI.getApi().getLocationAPI().getLocation("Spawn.Spawn"));
        }

        if (player.hasPermission("pb.admin")) {
            plugin.getInventoryManager().setInventory(player);
        }

        plugin.getScoreboardManager().createScoreboard(player);

        for (Player all : plugin.getServer().getOnlinePlayers()) {
            plugin.getScoreboardManager().updateScoreboard(all);
        }

        if (player.hasPermission("pb.admin")) {
            if (plugin.getMySQL().finishedWorlds.size() > 0) {
                if (plugin.getMySQL().finishedWorlds.size() == 1) {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Es gibt noch §beine §7unbearbeitete Welt§8.");
                } else {
                    player.sendMessage(plugin.getData().getPrefix() + "§7Es gibt noch §b" + plugin.getMySQL().finishedWorlds.size() + " §7unbearbeitete Welten§8.");
                }
            }
        }
    }

}
