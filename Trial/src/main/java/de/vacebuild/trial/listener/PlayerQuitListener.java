package de.vacebuild.trial.listener;

import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private Trialsystem plugin;

    public PlayerQuitListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        for (Player all : plugin.getServer().getOnlinePlayers()) {
            plugin.getScoreboardManager().updateScoreboard(all);
        }
    }

}
