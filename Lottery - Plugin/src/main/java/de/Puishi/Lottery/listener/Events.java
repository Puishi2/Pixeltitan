package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.commands.Command_Lottery;
import de.Puishi.Lobby.utils.Data;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 23:42                                               *
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
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked().getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
        e.setCancelled(!Command_Lottery.lotterylist.contains(player.getName()));

        if (e.getItemInHand().getType() == Material.SKULL_ITEM && e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Lottery")) {
            player.sendMessage(Data.PRERIX + "§7Du hast die Lottery gesetzt!");
            player.getInventory().clear();
            Main.getInstance().getLobbyInventory().setInventory(player);
            Command_Lottery.lotterylist.remove(player.getName());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        if (!(player.getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }

        if (Command_Lottery.lotterylist.contains(player.getName())) {
            e.setCancelled(true);
            Main.getInstance().getLocationAPI().saveBlockLocation(e.getBlock().getLocation(), "Lottery");
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(e.getBlock().getLocation().getBlock().getLocation().add(0.5D, -1.0D, 0.5D), EntityType.ARMOR_STAND);
            armorStand.setCustomName("§8* §6Lottery §8*");
            armorStand.setCustomNameVisible(true);
            armorStand.setCanPickupItems(false);
            armorStand.setGravity(false);
            armorStand.setVisible(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e) {
        if (!(e.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeizen(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.PHYSICAL) && e.getClickedBlock().getType().equals(Material.SOIL)) {
            e.setCancelled(true);
        }
    }


}
