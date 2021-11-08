package de.Puishi.TitanJump.scoreboard;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.perks.Perk_Manager;
import de.Puishi.TitanJump.points.PointsAPI;
import de.Puishi.TitanJump.team.TeamManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.awt.*;
import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 23.06.2020 / 10:57                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerScoreboard {

    private static final HashMap<Scoreboard, Player> board = new HashMap<>();

    public void setScoreboard(Player player) {

        board.clear();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8* §bTitanJump §8*");
        objective.getScore("§1").setScore(9);
        objective.getScore("§8■ §7Name").setScore(8);
        objective.getScore("§8● §b" + player.getName()).setScore(7);
        objective.getScore("§2").setScore(6);
        objective.getScore("§8■ §7Perk").setScore(5);
        objective.getScore("§4").setScore(3);
        objective.getScore("§8■ §7Server").setScore(2);
        objective.getScore("§8● §b" + CloudAPI.getInstance().getServerId()).setScore(1);
        objective.getScore("§b").setScore(0);

        Team team1 = scoreboard.registerNewTeam("x4");
        team1.setPrefix("§8●");
        if(!Perk_Manager.perk.containsKey(player.getUniqueId().toString())) {
            team1.setSuffix(" §c✗");
        /*if (!TeamManager.rot.contains(player.getUniqueId().toString()) || TeamManager.blau.contains(player.getUniqueId().toString())
                || TeamManager.grün.contains(player.getUniqueId().toString()) || TeamManager.gelb.contains(player.getUniqueId().toString())
                || TeamManager.lila.contains(player.getUniqueId().toString()) || TeamManager.pink.contains(player.getUniqueId().toString())
                || TeamManager.hellblau.contains(player.getUniqueId().toString()) || TeamManager.hellgrün.contains(player.getUniqueId().toString())) {
         */
        } else {
            /*if(TeamManager.rot.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §cRot");
            } else if(TeamManager.blau.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §9Blau");
            } else if(TeamManager.grün.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §2Grün");
            } else if(TeamManager.gelb.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §eGelb");
            } else if(TeamManager.lila.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §5Lila");
            } else if(TeamManager.pink.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §dPink");
            } else if(TeamManager.hellgrün.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §aHellgrün");
            } else if(TeamManager.hellblau.contains(player.getUniqueId().toString())) {
                team1.setSuffix(" §bHellblau");
            }
             */
            team1.setSuffix(" §b" + Perk_Manager.perk.get(player.getUniqueId().toString()).replace("§8»", ""));
        }
        team1.addEntry("§3");
        objective.getScore("§3").setScore(4);

        player.setScoreboard(scoreboard);

        board.put(scoreboard, player);

    }

    /*public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                    if (!(TeamManager.rot.contains(player.getUniqueId().toString()) || TeamManager.blau.contains(player.getUniqueId().toString())
                            || TeamManager.grün.contains(player.getUniqueId().toString()) || TeamManager.gelb.contains(player.getUniqueId().toString())
                            || TeamManager.lila.contains(player.getUniqueId().toString()) || TeamManager.pink.contains(player.getUniqueId().toString())
                            || TeamManager.hellblau.contains(player.getUniqueId().toString()) || TeamManager.hellgrün.contains(player.getUniqueId().toString()))) {
                        scoreboard.getTeam("x4").setSuffix("  §c✗");
                    } else {
                        if(TeamManager.rot.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §cRot");
                        } else if(TeamManager.blau.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §9Blau");
                        } else if(TeamManager.grün.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §2Grün");
                        } else if(TeamManager.gelb.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §eGelb");
                        } else if(TeamManager.lila.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §5Lila");
                        } else if(TeamManager.pink.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §dPink");
                        } else if(TeamManager.hellgrün.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §aHellgrün");
                        } else if(TeamManager.hellblau.contains(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §bHellblau");
                        }
                    }
                }

            }
        }.runTaskTimer(TitanJump.getInstance(), 0, 1);
    }
     */

    public void updatePerk() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                        if (!Perk_Manager.perk.containsKey(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x4").setSuffix(" §c✗");
                        } else {
                            scoreboard.getTeam("x4").setSuffix(Perk_Manager.perk.get(player.getUniqueId().toString()).replace("§8» ", " "));
                        }
                }
            }
        }.runTaskTimer(TitanJump.getInstance(), 0, 1);
    }

}
