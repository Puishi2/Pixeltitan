package de.Puishi.Lobby.listener;

import org.bukkit.*;
import org.bukkit.entity.Fish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.util.Vector;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 20.05.2020 / 14:25                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Enterhaken implements Listener {
    
    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Fish h = event.getHook();
        if ((event.getState().equals(PlayerFishEvent.State.IN_GROUND) || event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)) && Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.AIR && Bukkit.getWorld(event.getPlayer().getWorld().getName()).getBlockAt(h.getLocation().getBlockX(), h.getLocation().getBlockY() - 1, h.getLocation().getBlockZ()).getType() != Material.STATIONARY_WATER) {
            Location lc = player.getLocation();
            Location to = event.getHook().getLocation();
            lc.setY(lc.getY() + 0.5D);
            player.teleport(lc);
            double g = -0.08D;
            double d = to.distance(lc);
            double v_x = (1.0D + 0.07D * d) * (to.getX() - lc.getX()) / d;
            double v_y = (1.0D + 0.03D * d) * (to.getY() - lc.getY()) / d - 0.5D * g * d;
            double v_z = (1.0D + 0.07D * d) * (to.getZ() - lc.getZ()) / d;
            Vector v = player.getVelocity();
            v.setX(v_x);
            v.setY(v_y);
            v.setZ(v_z);
            player.setVelocity(v);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 5, 5);

            for (int degree = 0; degree < 360; degree++) {
                double radians = Math.toRadians(degree);
                double x = Math.cos(radians);
                double z = Math.sin(radians);
                to.add(x, 0, z);
                to.getWorld().playEffect(to, Effect.WITCH_MAGIC, 3);
                to.subtract(x, 0, z);
            }

        }
    }

}
