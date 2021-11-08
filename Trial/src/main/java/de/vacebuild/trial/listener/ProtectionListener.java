package de.vacebuild.trial.listener;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.UUID;

public class ProtectionListener implements Listener {

    private Trialsystem plugin;

    public ProtectionListener(Trialsystem plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void handleBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (plugin.getMySQL().getStatus(player.getUniqueId()) == 1) {
            if (player.getWorld().getName().contains(player.getUniqueId().toString())) {
                UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                if (uuid.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelled(true);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte warte§8, §7bis deine Welt §3bewertet §7wurde§8.");
                }
            } else {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
                } else {
                    event.setCancelled(!(player.hasPermission("pb.admin") || VaceAPI.getApi().isInBuildMode(player)));
                }
            }
        }

        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
        }
    }

    @EventHandler
    public void handleBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (plugin.getMySQL().getStatus(player.getUniqueId()) == 1) {
            if (player.getWorld().getName().contains(player.getUniqueId().toString())) {
                UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                if (uuid.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelled(true);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte warte§8, §7bis deine Welt §3bewertet §7wurde§8.");
                }
            } else {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
                } else {
                    event.setCancelled(!(player.hasPermission("pb.admin") || VaceAPI.getApi().isInBuildMode(player)));
                }
            }
        }

        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
        }
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (plugin.getMySQL().getStatus(player.getUniqueId()) == 1) {
            if (player.getWorld().getName().contains(player.getUniqueId().toString())) {
                UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                if (uuid.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelled(true);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte warte§8, §7bis deine Welt §3bewertet §7wurde§8.");
                }
            } else {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
                } else {
                    event.setCancelled(!(player.hasPermission("pb.admin") || VaceAPI.getApi().isInBuildMode(player)));
                }
            }
        }

        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
        }
    }

    @EventHandler
    public void handleEntityInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();

        if (plugin.getMySQL().getStatus(player.getUniqueId()) == 1) {
            if (player.getWorld().getName().contains(player.getUniqueId().toString())) {
                UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                if (uuid.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    event.setCancelled(true);

                    player.sendMessage(plugin.getData().getPrefix() + "§7Bitte warte§8, §7bis deine Welt §3bewertet §7wurde§8.");
                }
            } else {
                if (player.getWorld().getName().equalsIgnoreCase("world")) {
                    event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
                } else {
                    event.setCancelled(!(player.hasPermission("pb.admin") || VaceAPI.getApi().isInBuildMode(player)));
                }
            }
        }

        if (player.getWorld().getName().equalsIgnoreCase("world")) {
            event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
        }
    }


    @EventHandler
    public void handleDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if (plugin.getMySQL().getStatus(player.getUniqueId()) == 1) {
                if (player.getWorld().getName().contains(player.getUniqueId().toString())) {
                    UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                    if (uuid.toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                        event.setCancelled(true);

                        player.sendMessage(plugin.getData().getPrefix() + "§7Bitte warte§8, §7bis deine Welt §3bewertet §7wurde§8.");
                    }
                } else {
                    if (player.getWorld().getName().equalsIgnoreCase("world")) {
                        event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
                    } else {
                        event.setCancelled(!(player.hasPermission("pb.admin") || VaceAPI.getApi().isInBuildMode(player)));
                    }
                }
            }

            if (player.getWorld().getName().equalsIgnoreCase("world")) {
                event.setCancelled(!VaceAPI.getApi().isInBuildMode(player));
            }
        }
    }

    @EventHandler
    public void handleBlockPhysics(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleFoodChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void handleWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();

        plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
            plugin.getScoreboardManager().updateScoreboard(player), 3L);
    }

}
