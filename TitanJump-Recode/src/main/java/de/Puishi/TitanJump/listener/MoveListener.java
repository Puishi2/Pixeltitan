package de.Puishi.TitanJump.listener;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.points.PointsAPI;
import de.Puishi.TitanJump.utils.ActionBar;
import de.javaarray.pixeltitan.coinsapi.api.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 23.06.2020 / 11:18                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class MoveListener implements Listener {

    public static HashMap<String, Location> locationHashMap = new HashMap<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(TitanJump.getInstance().getGameState() == GameState.STARTING) {
            event.setCancelled(true);
        }

        /**
         * Player on Checkpoint
         */

        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GOLD_BLOCK) {
            if(!locationHashMap.containsKey(player.getUniqueId().toString())) {
                locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());
                PointsAPI.setPoints(player, 20);
                new ActionBar().setTitle(player, "§8* §b§lCheckpoint §8*", "§7§l+20 Points", 10, 20, 10);
                ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                CoinsAPI.addCoins(player.getUniqueId().toString(), 10);
            } else {
                if (locationHashMap.get(player.getUniqueId().toString()).getX() == player.getLocation().getBlock().getLocation().getX()
                        && locationHashMap.get(player.getUniqueId().toString()).getZ() == player.getLocation().getBlock().getLocation().getZ()
                        && locationHashMap.get(player.getUniqueId().toString()).getY() == player.getLocation().getBlock().getLocation().getY()) {
                } else {
                    locationHashMap.remove(player.getUniqueId().toString());
                    locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());
                    PointsAPI.setPoints(player, 20);
                    new ActionBar().setTitle(player, "§8* §b§lCheckpoint §8*", "§7§l+20 Points", 10, 20, 10);
                    ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    CoinsAPI.addCoins(player.getUniqueId().toString(), 10);
                }
            }

            /**
             * Player into goal
             */

        } else if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
            if(!locationHashMap.containsKey(player.getUniqueId().toString())) {
                locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());
                PointsAPI.setPoints(player, 50);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    new ActionBar().setTitle(all, "§8* §b§lZiel §8*", "§b§l" + player.getName() + " §7§lhat das Ziel erreicht", 10, 20, 10);
                }
                ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                TitanJump.getInstance().setGameState(GameState.SHOPPING);
                CoinsAPI.addCoins(player.getUniqueId().toString(), 20);
            } else {
                if (locationHashMap.get(player.getUniqueId().toString()).getX() == player.getLocation().getBlock().getLocation().getX()
                        && locationHashMap.get(player.getUniqueId().toString()).getZ() == player.getLocation().getBlock().getLocation().getZ()
                        && locationHashMap.get(player.getUniqueId().toString()).getY() == player.getLocation().getBlock().getLocation().getY()) {
                } else {
                    locationHashMap.remove(player.getUniqueId().toString());
                    locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());
                    PointsAPI.setPoints(player, 50);
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        new ActionBar().setTitle(all, "§8* §b§lZiel §8*", "§b§l" + player.getName() + " §7§lhat das Ziel erreicht", 10, 20, 10);
                    }
                    ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    TitanJump.getInstance().setGameState(GameState.SHOPPING);
                    CoinsAPI.addCoins(player.getUniqueId().toString(), 20);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.getLocation().getWorld().setTime(6000);
                                all.setGameMode(GameMode.SURVIVAL);
                            }
                        }
                    }.runTaskLater(TitanJump.getInstance(), 100);
                }
            }

            /**
             * First Checkpoint (Playerteleport)
             */

        } else if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STAINED_CLAY) {
            if(!locationHashMap.containsKey(player.getUniqueId().toString())) {
                locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());
            } else {
                if (locationHashMap.get(player.getUniqueId().toString()).getX() == player.getLocation().getBlock().getLocation().getX()
                        && locationHashMap.get(player.getUniqueId().toString()).getZ() == player.getLocation().getBlock().getLocation().getZ()
                        && locationHashMap.get(player.getUniqueId().toString()).getY() == player.getLocation().getBlock().getLocation().getY()) {
                } else {
                    locationHashMap.remove(player.getUniqueId().toString());
                    locationHashMap.put(player.getUniqueId().toString(), player.getLocation().getBlock().getLocation());

                }
            }
        }

        /**
         * Teleport when player falling into the Void
         */
        if(TitanJump.getInstance().getGameState() == GameState.INGAME) {
            if (player.getLocation().getY() < 60.0D) {
                player.teleport(locationHashMap.get(player.getUniqueId().toString()).clone().add(0.5, 3, 0.5));
                PointsAPI.removePoints(player, 5);
                new ActionBar().setTitle(player, "§8* §b§lCheckpoint §8*", "§c§l-5 Points", 10, 20, 10);
                ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
            }
        }

        if(TitanJump.getInstance().getGameState() == GameState.LOBBY) {
            if(player.getLocation().getY() < 60.0D) {
            }
        }

    }

}
