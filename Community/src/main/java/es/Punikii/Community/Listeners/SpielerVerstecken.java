package es.Punikii.Community.Listeners;

import java.util.ArrayList;

import es.Punikii.Community.Community;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpielerVerstecken implements Listener{

    public static ArrayList<Player> PlayerVersteckt = new ArrayList();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getItem().getType() == Material.BLAZE_ROD) {
                if(SpielerVerstecken.PlayerVersteckt.contains(p)) {
                    SpielerVerstecken.PlayerVersteckt.remove(p);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(all);
                    }

                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 3);
                    p.sendMessage(Community.prefix + "Alle Spieler sind nun sichtbar.");

                }else {
                    SpielerVerstecken.PlayerVersteckt.add(p);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(all);
                    }

                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 2, 3);
                    p.sendMessage(Community.prefix + "Alle Spieler wurden versteckt.");

                }
            }
        }
    }
}