package de.Puishi.SkyWars.listener;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.utils.ItemBuilder;
import de.Puishi.SkyWars.utils.SpecUtils;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 25.05.2020 / 11:34                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Events implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING);
    }

    /*@EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (Main.getInstance().getGameStates() == GameStates.INGAME) {
            if (e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
                e.setCancelled(true);
                Inventory inventory = Bukkit.createInventory(null, InventoryType.ENCHANTING, "§8» §2Verzaubern");
                inventory.setItem(1, new ItemBuilder(Material.INK_SACK).setDyeColor(DyeColor.BLUE).setName("§8» §9Lapis").setAmount(64).toItemStack());
                e.getPlayer().openInventory(inventory);
            }
        }
    }

     */

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        e.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING || SpecUtils.spectatorlist.contains(e.getWhoClicked()));
    }

    @EventHandler
    public void onBlockBr(BlockBreakEvent e) {
        e.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING || SpecUtils.spectatorlist.contains(event.getPlayer()));
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        event.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING || SpecUtils.spectatorlist.contains(event.getPlayer()));
    }

    @EventHandler
    public void EntityDamageEventByEntety(EntityDamageByEntityEvent e) {
        e.setCancelled(Main.getInstance().getGameStates() == GameStates.LOBBY || Main.getInstance().getGameStates() == GameStates.ENDING);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreature(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }
}
