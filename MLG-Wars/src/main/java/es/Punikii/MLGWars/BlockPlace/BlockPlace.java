package es.Punikii.MLGWars.BlockPlace;

import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class BlockPlace implements Listener {

    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if (event.getBlock().getType() == Material.WEB || event.getBlock().getType() == Material.SLIME_BLOCK || event.getBlock().getType() == Material.SANDSTONE) {
            if (MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
                final Block block = event.getBlock();
                Bukkit.getScheduler().runTaskLater(MLGWars.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        block.setType(Material.AIR);
                    }
                }, 20);
            }
        }
    }

    @EventHandler
    public void onBucketEmpty (PlayerBucketEmptyEvent event) {
        if(event.getBucket() != Material.WATER_BUCKET) return;
        Player player = event.getPlayer();
        Block water = event.getBlockClicked().getRelative(event.getBlockFace());
        new BukkitRunnable() {
            @Override
            public void run() {
                water.setType(Material.AIR);
            }
        }.runTaskLater(MLGWars.getInstance(), 20);
    }

    @EventHandler
    public void boot(VehicleCreateEvent event) {
        if(event.getVehicle() instanceof Boat) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getVehicle().remove();
                }
            }.runTaskLater(MLGWars.getInstance(), 20);
        }
    }
}
