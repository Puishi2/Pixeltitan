package de.Puishi.BedWars.scoreboard;

import de.Puishi.BedWars.Main;
import de.Puishi.BedWars.utils.Data;
import de.Puishi.BedWars.utils.Teammanager;
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
 *    Erstellt: 18.05.2020 / 17:26                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerScoreboard implements IScoreboardUtil {

    public HashMap<Scoreboard, Player> board = new HashMap<>();
    protected static int go;

    @Override
    public void set(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(Data.PREFIX);

        Team team = scoreboard.registerNewTeam("x8");
        team.setPrefix("§8");
        team.setSuffix("  §8■ §7Team");
        team.addEntry("§3");
        objective.getScore("§3").setScore(8);

        Team team1 = scoreboard.registerNewTeam("x7");
        team1.setPrefix("    §8● ");
        team1.setSuffix(Teammanager.getTeam(player));
        team1.addEntry("§4");
        objective.getScore("§4").setScore(7);

        board.put(scoreboard, player);

        player.setScoreboard(scoreboard);
    }

    public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                    scoreboard.getTeam("x7").setSuffix("    §8●" + Teammanager.getTeam(player));
                    player.setScoreboard(scoreboard);
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

}
