package de.Puishi.Bauserver.scoreboard;

import de.Puishi.Bauserver.Bauserver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 07.06.2020 / 12:31                                               *
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
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaaa", "bbbb");

        String rang = "§7Spieler";
        if (player.hasPermission("pixel.hadmin")) {
            rang = "§4Head-Admin";
        } else if (player.hasPermission("pixel.admin")) {
            rang = "§4Administrator";
        } else if (player.hasPermission("pixel.ca")) {
            rang = "§4Community-Admin";
        } else if (player.hasPermission("pixel.srdev")) {
            rang = "§bSrDeveloper";
        } else if (player.hasPermission("pixel.dev")) {
            rang = "§bDeveloper";
        } else if (player.hasPermission("pixel.srcontent")) {
            rang = "§bSrContent";
        } else if (player.hasPermission("pixel.content")) {
            rang = "§bContent";
        } else if (player.hasPermission("pixel.srmod")) {
            rang = "§cSrModerator";
        } else if (player.hasPermission("pixel.srbuilder")) {
            rang = "§eSrBuider";
        } else if (player.hasPermission("pixel.mod")) {
            rang = "§cModerator";
        } else if (player.hasPermission("pixel.builder")) {
            rang = "§eBuilder";
        } else if (player.hasPermission("pixel.sup")) {
            rang = "§9Suppoter";
        } else if (player.hasPermission("pixel.yt")) {
            rang = "§5YouTuber";
        } else if (player.hasPermission("pixel.pixel")) {
            rang = "§aPixel";
        } else if (player.hasPermission("pixel.premium")) {
            rang = "§6Premium";
        } else {
            rang = "§7Spieler";
        }

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8* §3Bauserver §8*");

        objective.getScore("§1").setScore(9);
        objective.getScore("§8■ §7Name    ").setScore(8);
        objective.getScore(" §8● §a" + player.getName()).setScore(7);
        objective.getScore("§d").setScore(6);
        objective.getScore("§8■ §7Rang    ").setScore(5);
        objective.getScore(" §8● §a" + rang).setScore(4);
        objective.getScore("§2").setScore(3);
        objective.getScore("§8■ §7Map").setScore(2);
        objective.getScore("§9").setScore(0);

        Team team = scoreboard.registerNewTeam("x1");
        team.setPrefix("§8");
        team.setSuffix(" §8● §3" + player.getWorld().getName());
        team.addEntry("§3");
        objective.getScore("§3").setScore(1);

        player.setScoreboard(scoreboard);
        board.put(scoreboard, player);

    }

    public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                    scoreboard.getTeam("x1").setSuffix(" §8● §3" + player.getWorld().getName());
                }
            }
        }.runTaskTimer(Bauserver.getInstance(), 0, 1);
    }
}
