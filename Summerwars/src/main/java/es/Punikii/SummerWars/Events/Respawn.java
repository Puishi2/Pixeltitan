package es.Punikii.SummerWars.Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class Respawn implements Listener {

    public Plugin plugin;
    public static final int RESPAWN_TIME = 0;

    public Respawn(Plugin plugin) { this.plugin = plugin; }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {

            @Override
            public void run() {
                if(player != null) {
                    if(player.isDead()) {
                        player.spigot().respawn();
                    }
                }
            }
        }, 20 * RESPAWN_TIME);
    }
}

