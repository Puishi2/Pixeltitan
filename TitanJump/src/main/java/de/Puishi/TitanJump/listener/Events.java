package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 26.06.2020 / 08:52                                               *
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
        if(TitanJump.getInstance().getGameState() == GameState.LOBBY || TitanJump.getInstance().getGameState() == GameState.DEATHMATCHCOUNTDOWN|| TitanJump.getInstance().getGameState() == GameState.STARTING || TitanJump.getInstance().getGameState() == GameState.INGAME || TitanJump.getInstance().getGameState() == GameState.SHOPPING || TitanJump.getInstance().getGameState() == GameState.ENDING) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if(TitanJump.getInstance().getGameState() == GameState.LOBBY || TitanJump.getInstance().getGameState() == GameState.STARTING || TitanJump.getInstance().getGameState() == GameState.INGAME || TitanJump.getInstance().getGameState() == GameState.SHOPPING || TitanJump.getInstance().getGameState() == GameState.ENDING) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!(event.getPlayer().getGameMode() == GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if(TitanJump.getInstance().getGameState() == GameState.LOBBY || TitanJump.getInstance().getGameState() == GameState.STARTING || TitanJump.getInstance().getGameState() == GameState.INGAME ||  TitanJump.getInstance().getGameState() == GameState.ENDING) {
            event.setCancelled(true);
        }
        if(event.getInventory().getName().equalsIgnoreCase("§8» §bShop")) {
            event.setCancelled(true);
        }

        if(event.getInventory().getName().equalsIgnoreCase("§8» §bRüstungen")) {
            event.setCancelled(true);
        }

        if(event.getInventory().getName().equalsIgnoreCase("§8» §bSonstiges")) {
            event.setCancelled(true);
        }
        if(event.getInventory().getName().equalsIgnoreCase("§8» §bVerzauberungen")) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if(TitanJump.getInstance().getGameState() == GameState.LOBBY || TitanJump.getInstance().getGameState() == GameState.STARTING || TitanJump.getInstance().getGameState() == GameState.INGAME || TitanJump.getInstance().getGameState() == GameState.SHOPPING || TitanJump.getInstance().getGameState() == GameState.ENDING) {
            event.setCancelled(true);
        }
    }

}
