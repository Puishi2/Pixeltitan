package es.Punikii.MLGWars.Listeners;

import es.Punikii.MLGWars.Locations.LocationManager;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.countdown.EndCountdown;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class OnQuit implements Listener {

    public static ArrayList<Player> build = new ArrayList<Player>();

    public MLGWars plugin;

    public OnQuit(MLGWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){

        Player p = e.getPlayer();
        MLGWars.playlist.remove(p.getName());
        LocationManager lm = new LocationManager();

        p.getInventory().clear();
        p.setExp(0);

        e.setQuitMessage(MLGWars.prefix + "Der Spieler §c" + e.getPlayer().getName() + " §7hat das Spiel verlassen!");

        if (MLGWars.getInstance().getGameStates() == GameStates.PVP || MLGWars.getInstance().getGameStates() == GameStates.INGAME
                || MLGWars.getInstance().getGameStates() == GameStates.SHOP) {
            if (MLGWars.playlist.size() == 1) {
                MLGWars.getInstance().setGameStates(GameStates.ENDING);
                new EndCountdown().startcountdown(p);
                Bukkit.getPlayer(MLGWars.playlist.get(0)).teleport(LocationManager.getLocation("Spawn"));
            }
        }
    }
}