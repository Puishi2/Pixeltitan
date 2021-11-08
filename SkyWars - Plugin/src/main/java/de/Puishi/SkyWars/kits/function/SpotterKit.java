package de.Puishi.SkyWars.kits.function;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.utils.SpecUtils;
import javafx.print.PageLayout;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 29.05.2020 / 00:44                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class SpotterKit implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        try {
            Player player = event.getPlayer();

            if(Main.getInstance().getGameStates() == GameStates.INGAME) {
                if(!SpecUtils.spectatorlist.contains(player)) {
                    if(event.getItem().getType() == Material.GLOWSTONE_DUST
                            && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §7Spotter")) {
                        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
                            double distance = 1000;
                            Player playernull = null;
                            for(Entity entity : player.getNearbyEntities(100, 25, 100)) {
                                if(entity instanceof Player) {
                                    Player p = (Player) entity;
                                    if(player != p && player.getLocation().distance(p.getLocation()) < distance) {
                                        distance = player.getLocation().distance(p.getLocation());
                                        playernull = p;
                                    }
                                }
                            }
                            player.sendMessage(Data.PREFIX + "Der Spieler §2" + playernull.getName() + " §7ist §2" + (int) distance + " §7Blöcke entfernt!");
                        }
                    }
                }
            }
        } catch (Exception e) {}

    }

}
