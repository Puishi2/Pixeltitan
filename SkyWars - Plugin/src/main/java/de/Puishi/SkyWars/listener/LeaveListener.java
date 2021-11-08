package de.Puishi.SkyWars.listener;

import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.countdown.LobbyCountdown;
import de.Puishi.SkyWars.utils.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 05.06.2020 / 20:29                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LeaveListener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem() != null){
            if(event.getItem().getItemMeta().getDisplayName() != null){
                if(event.getItem().getType() == Material.SKULL_ITEM &&
                        event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cVerlassen")) {
                    player.kickPlayer(Data.PREFIX + "Du hast das Spiel verlassen!");
                }
            }
        }
    }
}
