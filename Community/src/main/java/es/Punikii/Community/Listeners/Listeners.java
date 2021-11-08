package es.Punikii.Community.Listeners;

import java.util.ArrayList;

import es.Punikii.Community.Community;
import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Listeners implements Listener {

    public static Object build;

    ArrayList<Player> items = new ArrayList<Player>();

    private LocationManager locationManager = new LocationManager();

    private Community plugin;
    public Listeners(Community plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if(!(OnJoin.build.contains(player))) {
            e.setCancelled(true);

        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        if(OnJoin.build.contains(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(OnJoin.build.contains(e.getPlayer())) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
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
        Player player = (Player) event.getEntity();
        if (event.getDamager() instanceof Player){
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onCancelFallDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if(OnJoin.build.contains(player)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    private Object getPlayer() {
        // TODO Auto-generated method stub
        return null;
    }
}