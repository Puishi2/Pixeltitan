package es.Punikii.Community.Listeners;
import java.util.ArrayList;

import es.Punikii.Community.Manager.LocationManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import es.Punikii.Community.Community;

public class OnQuit implements Listener {

    public static ArrayList<Player> build = new ArrayList<Player>();

    public Community plugin;

    public OnQuit(Community plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){

        Player p = e.getPlayer();
        LocationManager lm = new LocationManager();

        e.setQuitMessage("");

    }
}