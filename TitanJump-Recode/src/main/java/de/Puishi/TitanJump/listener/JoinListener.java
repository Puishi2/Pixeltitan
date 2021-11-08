package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.countdown.LobbyCountdown;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LobbyInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.07.2020 / 20:34                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(Data.getPrefix() + "Der Spieler §b" + event.getPlayer().getName() + " §7hat das Spiel betreten!");

        new LobbyInventory().setInventory(player);

        if(Bukkit.getServer().getOnlinePlayers().size() == new Data().getMinPlayers()) {
            new LobbyCountdown().start(player);
        }

    }

}
