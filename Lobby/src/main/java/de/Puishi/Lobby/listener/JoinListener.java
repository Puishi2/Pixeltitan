package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.database.MySQL;
import de.Puishi.Lobby.utils.Actionbar;
import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.LocationAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 21:19                                               *
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
    public void onJoin(final PlayerJoinEvent e){
        Location loc = e.getPlayer().getLocation();
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        p.setHealth(20);
        p.setHealthScale(8);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.SURVIVAL);
        Actionbar.setTitle(p, "§8§l« §6PixelTitan.net §8§l»", "", 1, 20*2, 1);
        p.teleport(new LocationAPI().getLocation("Spawn"));
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
        new PlayerScoreboard().setScoreboard(p);

        p.getLocation().getWorld().strikeLightningEffect(p.getLocation());

        Main.getInstance().getLobbyInventory().setInventory(p);

        Main.getInstance().getVisibleHandler().getHide().forEach(hider -> {
            hider.hidePlayer(p);
        });
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e){
        e.setQuitMessage(null);
    }
}
