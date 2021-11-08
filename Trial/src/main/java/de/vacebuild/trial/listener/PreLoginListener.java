package de.vacebuild.trial.listener;

import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PreLoginListener implements Listener {

    private Trialsystem plugin;

    public PreLoginListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @Deprecated
    @EventHandler
    public void handlePreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        if (!plugin.getMySQL().isPlayerExists(uuid)) {
            plugin.getMySQL().create(uuid);
        } else {
            plugin.getMySQL().load(uuid);
        }
    }

}
