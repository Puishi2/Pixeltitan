package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.utils.ActionBar;
import de.Puishi.Lobby.utils.LobbyInventory;
import de.Puishi.Lobby.utils.LocationManager;
import de.Puishi.Lobby.utils.PlayerScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 08.07.2020 / 17:25                                               *
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
    public void onJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null);

        for(Player all : Bukkit.getOnlinePlayers()) {
            new LobbyInventory().setInventory(player);
        }

        player.teleport(new LocationManager().getLocation("Spawn"));

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealthScale(8);
        player.setHealth(20);
        player.setFoodLevel(20);

        new PlayerScoreboard().setScoreboard(player);

        ActionBar.setTitle(player, "§8§l* §3§lPixelBuilds §8§l*", "§7Willkommen", 10, 20, 10);

    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

}
