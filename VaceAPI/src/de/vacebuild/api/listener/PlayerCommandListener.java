package de.vacebuild.api.listener;

import de.vacebuild.api.main.VaceAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {

    private VaceAPI plugin;

    public PlayerCommandListener(VaceAPI plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (plugin.getServer().getHelpMap().getHelpTopic(event.getMessage().split(" ")[0]) == null) {
            plugin.getData().noPerms(player);
            event.setCancelled(true);
        }
    }

}
