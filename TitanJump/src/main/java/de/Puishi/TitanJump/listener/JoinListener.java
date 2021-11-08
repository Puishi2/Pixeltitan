package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.countdown.LobbyCountdown;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.points.PointsAPI;
import de.Puishi.TitanJump.scoreboard.PlayerScoreboard;
import de.Puishi.TitanJump.utils.ActionBar;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LobbyInventory;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import javax.persistence.Lob;
import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.06.2020 / 19:39                                               *
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

        event.setJoinMessage(Data.getPrefix() + "Der Spieler §b" + player.getName() + " §7hat das Spiel betreten!");

        player.teleport(new LocationManager().getLocation("Waiting-Lobby"));

        for(Player all : Bukkit.getOnlinePlayers()) {
            Data.playerlist.add(all.getName());
            all.setGameMode(GameMode.SURVIVAL);
            all.setFoodLevel(20);
            all.setHealth(20);
            all.setExp(0);
            all.setLevel(0);
            all.getLocation().getWorld().setTime(6000);
            PointsAPI.points.clear();
            PointsAPI.setPoints(player, 50);
        }

        if(Bukkit.getOnlinePlayers().size() == TitanJump.getInstance().getMinplayers()) {
            if(!new LobbyCountdown().isRunning) {
                new LobbyCountdown().start(player);
            }
        }

        if(TitanJump.getInstance().getGameState() == GameState.LOBBY) {
            new LobbyInventory().setInventory(player);
        }

        new PlayerScoreboard().setScoreboard(player);

    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(Data.getPrefix() + "Der Spieler §b" + event.getPlayer().getName() + " §7hat das Spiel verlassen!");
    }

}
