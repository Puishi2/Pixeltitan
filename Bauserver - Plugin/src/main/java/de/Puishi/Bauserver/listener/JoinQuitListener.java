package de.Puishi.Bauserver.listener;

import de.Puishi.Bauserver.scoreboard.PlayerScoreboard;
import de.Puishi.Bauserver.utils.ActionBar;
import de.Puishi.Bauserver.utils.Data;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 07.06.2020 / 12:23                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(Data.getPREFIX() + "Der Spieler §3" + event.getPlayer().getName() + " §7hat den Bauserver betreten!");

        new PlayerScoreboard().setScoreboard(player);

        player.setGameMode(GameMode.CREATIVE);
        ActionBar.setTitle(player, "§8§l« §3Bauserver §8§l»", "", 1, 20*2, 1);

    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event){
        event.setQuitMessage(Data.getPREFIX() + "Der Spieler §3" + event.getPlayer().getName() + " §7hat den Bauserver verlassen!");
    }

}
