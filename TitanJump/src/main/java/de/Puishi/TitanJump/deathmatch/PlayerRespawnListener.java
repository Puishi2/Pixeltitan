package de.Puishi.TitanJump.deathmatch;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.07.2020 / 23:35                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();

        if(PlayerManager.getLives().get(player) > 0) {
            player.teleport(new LocationManager().getLocation("deathmatch"));

            Bukkit.getScheduler().runTaskLater(TitanJump.getInstance(), new Runnable() {
                @Override
                public void run() {
                    player.getInventory().setContents(player.getInventory().getContents());
                    player.getInventory().setArmorContents(player.getInventory().getArmorContents());
                }
            }, 13L);

        } else {
            new PlayerManager().setSpectator(player);
        }

    }

}
