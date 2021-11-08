package de.vacebuild.api.listener;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private VaceAPI plugin;

    public PlayerJoinListener(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("pixel.admin")) {
            for (Player all : plugin.getServer().getOnlinePlayers()) {
                if (plugin.isInVanishMode(all)) {
                    player.hidePlayer(all);
                }
            }
        }
    }

}
