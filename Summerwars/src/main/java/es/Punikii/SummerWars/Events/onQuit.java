package es.Punikii.SummerWars.Events;

import es.Punikii.SummerWars.Managers.LocationManager;
import es.Punikii.SummerWars.SummerWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;


public class onQuit implements Listener {

    public SummerWars plugin;

    public onQuit(SummerWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){

        Player p = e.getPlayer();

        p.teleport(LocationManager.getLocation("Spawn"));



        e.setQuitMessage("§7[§c-§7] §8" + e.getPlayer().getName());
    }
}