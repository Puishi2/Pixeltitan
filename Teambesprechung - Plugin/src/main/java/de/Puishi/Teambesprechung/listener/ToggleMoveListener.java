package de.Puishi.Teambesprechung.listener;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.commands.Command_togglemove;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 19:16                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class ToggleMoveListener implements Listener {

    double walkspeed0 = 0.1;

    @EventHandler
    public void onMove(final PlayerMoveEvent event){
        Player p = event.getPlayer();

        if(Command_togglemove.togglemovelist.contains(p.getName())){
            for (Player all : Bukkit.getOnlinePlayers()){
                event.setCancelled(false);
            }
        }else if(PlayerInventoryClick.togglemovelist.contains(p.getName())){
            for (Player all : Bukkit.getOnlinePlayers()){
                event.setCancelled(true);
            }
        }

    }

    /*
    for (Player all : Bukkit.getOnlinePlayers()){
                event.setCancelled(false);
            }

    for (Player all : Bukkit.getOnlinePlayers()){
                event.setCancelled(true);
            }
     */
}
