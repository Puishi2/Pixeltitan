package es.Punikii.SummerWars.Events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Water implements Listener{

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (p.getLocation().getBlock().getType() == Material.WATER
                || (p.getLocation().getBlock().getType() == Material.STATIONARY_WATER)) {
            p.setHealth(0);
        }
    }
}