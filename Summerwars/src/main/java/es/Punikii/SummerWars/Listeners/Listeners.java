package es.Punikii.SummerWars.Listeners;

import java.util.ArrayList;

import es.Punikii.SummerWars.Managers.ItemManager;
import es.Punikii.SummerWars.Managers.LocationManager;
import es.Punikii.SummerWars.SummerWars;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Listeners implements Listener {

    ArrayList<Player> items = new ArrayList<Player>();

    private LocationManager locationManager;

    private SummerWars plugin;
    public Listeners(SummerWars plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onIMove(InventoryClickEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onBrake(BlockBreakEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
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
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().clear();
        Player p =  e.getEntity();
        Player k =  e.getEntity().getKiller();
        if(e.getEntity().getKiller() instanceof Player && !e.getEntity().getKiller().equals(p)){
            k.playSound(k.getLocation(), Sound.SUCCESSFUL_HIT, 5F, 5F);
            k.sendTitle("§a✔", p.getName());
            p.sendTitle("§4✖", k.getName());
            k.setHealth(20);
            p.teleport(LocationManager.getLocation("Spawn"));
        }
    }
    @EventHandler
    public void onCancelFallDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) { //Checks to see if the entity that is taking damage is a player
            if(e.getCause() == DamageCause.FALL) { //if the cause of damage is fall damage
                e.setCancelled(true); //you cancel the event.
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§8» §6SummerWars") ||
                !player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(" ")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        if(player.getLocation().distance(locationManager.getLocation("Spawn")) <= 10.0D) {
            if (event.getDamager() instanceof Player){
                if (event.getEntity() instanceof Player) {
                    event.setCancelled(true);
                }
            }

        }
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (event.getPlayer().getLocation().distance(locationManager.getLocation("Spawn")) <= 10.0D) {
            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("SummerWars") ||
                    player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("SummerWars")) {
                return;
            }else
                player.getInventory().setItem(0, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(1, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(2, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(3, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(4, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(5, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(6, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(7, ItemManager.createItem(Material.AIR, 15, " ", 1));
            player.getInventory().setItem(8, ItemManager.createItem(Material.AIR, 15, " ", 1));
        }else {

            if (player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7WasserPistole") || player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§8» §7WasserBombe") ||
                    player.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("SummerWars")) {
                return;
            }else

                player.getInventory().clear();
            player.getInventory().setItem(0, ItemManager.createItem(Material.WOOD_HOE, 0, "§8» §7WasserPistole", 1));
            player.getInventory().setItem(1, ItemManager.createItem(Material.SLIME_BALL, 0, "§8» §7WasserBombe", 1));
        }
    }
}