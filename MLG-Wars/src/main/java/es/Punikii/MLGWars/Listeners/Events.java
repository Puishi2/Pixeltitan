package es.Punikii.MLGWars.Listeners;

import com.google.common.eventbus.DeadEvent;
import es.Punikii.MLGWars.Lives.Lives;
import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.countdown.EndCountdown;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Events implements Listener {

    private LocationManager locationManager;

    public HashMap<Player , ItemStack[]> items = new HashMap<Player , ItemStack[]>();

    @EventHandler
    public void Food(FoodLevelChangeEvent event) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP
                || MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
            if (OnJoin.build.contains(event.getPlayer())) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP
                || MLGWars.getInstance().getGameStates() == GameStates.INGAME || MLGWars.getInstance().getGameStates() == GameStates.PVP) {
            e.setCancelled(true);
        Player player = e.getPlayer();
        if (OnJoin.build.contains(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP || MLGWars.getInstance().getGameStates() == GameStates.PVP){
            e.setCancelled(true);
        Player player = e.getPlayer();
            if (OnJoin.build.contains(e.getPlayer())) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void OnWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void Frames(HangingBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP
                || MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
            event.setCancelled(true);
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCancelFallDamage(EntityDamageEvent e) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP
                || MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (MLGWars.getInstance().getGameStates() == GameStates.LOBBY || MLGWars.getInstance().getGameStates() == GameStates.SHOP
                || MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
            e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        if (OnJoin.build.contains(player)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        ItemStack[] content = e.getEntity().getInventory().getContents();
        items.put(e.getEntity(), content);
        e.getEntity().getInventory().clear();
        Lives.lives.put(p, Lives.lives.get(p) - 1);
        if(Lives.lives.get(p) == 0) {
            p.setGameMode(GameMode.SPECTATOR);
            MLGWars.playlist.remove(p.getName());
            if (MLGWars.playlist.size() == 1) {
                MLGWars.getInstance().setGameStates(GameStates.ENDING);
                new EndCountdown().startcountdown(p);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    all.teleport(LocationManager.getLocation("Spawn"));
                    all.setGameMode(GameMode.SURVIVAL);
                }
            }
        }
    }
    @EventHandler()
    public void onRespawn(PlayerRespawnEvent event){
        if(items.containsKey(event.getPlayer())){
            event.getPlayer().getInventory().clear();
            for(ItemStack stack : items.get(event.getPlayer())){
                event.getPlayer().getInventory().addItem(stack);
            }

            items.remove(event.getPlayer());
        }
    }
    @EventHandler
    public void time(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void block(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos2").getY()) {
            e.setCancelled(true);
        }
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos3").getY()) {
            e.setCancelled(true);
        }
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos4").getY()) {
            e.setCancelled(true);
        }
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos5").getY()) {
            e.setCancelled(true);
        }
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos6").getY()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if (p.getLocation().getY() >= LocationManager.getLocation("Pos1").getY()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        if(MLGWars.getInstance().getGameStates() == GameStates.INGAME) {
            int id = event.getBlock().getTypeId();
            if(id == 8 || id == 9) {
                event.setCancelled(true);
            }
        }
    }
}
