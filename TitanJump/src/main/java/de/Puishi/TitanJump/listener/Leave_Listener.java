package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 03.07.2020 / 09:11                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Leave_Listener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if(event.getItem() != null) {
            if (event.getItem().getItemMeta().getDisplayName() != null) {
                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cVerlassen")) {
                    Player player = event.getPlayer();
                    player.kickPlayer(Data.getPrefix() + "§cDu hast das Spiel verlassen!");
                }
            }
        }
    }

}
