package de.Puishi.BedWars.listener;

import de.Puishi.BedWars.Main;
import de.Puishi.BedWars.gamestate.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.06.2020 / 20:16                                               *
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
    public void onDamage(EntityDamageEvent event){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING){
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onBlockBr(BlockBreakEvent e){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING){
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING){
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING){
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void EntityDamageEventByEntety(EntityDamageByEntityEvent e){
        if(Main.getInstance().getGameState() == GameState.LOBBY || Main.getInstance().getGameState() == GameState.ENDING){
            e.setCancelled(true);
        }else{
            e.setCancelled(false);
        }
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
