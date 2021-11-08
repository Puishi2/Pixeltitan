package de.Puishi.Lobby.utils;

import com.avaje.ebean.validation.Email;
import de.dytanic.cloudnet.api.CloudAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 08.07.2020 / 17:42                                               *
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

    public void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8§l* §3§lPixelBuild §8§l*");
        objective.getScore("§1").setScore(9);
        objective.getScore("§8■ §7Name").setScore(8);
        objective.getScore("§8● §3" + player.getName()).setScore(7);
        objective.getScore("§2").setScore(6);
        objective.getScore("§8■ §7Rang").setScore(5);
        if(player.hasPermission("lobby.bl")) {
            objective.getScore("§8● §4Leitung").setScore(4);
        } else if(player.hasPermission("lobby.dev")) {
            objective.getScore("§8● §bTeam").setScore(4);
        } else if(player.hasPermission("lobby.builder")) {
            objective.getScore("§8● §3Builder").setScore(4);
        } else {
            objective.getScore("§8● §eBewerber").setScore(4);
        }
        objective.getScore("§3").setScore(3);
        objective.getScore("§8■ §7Server").setScore(2);
        objective.getScore("§8● §3" + CloudAPI.getInstance().getServerId()).setScore(1);
        objective.getScore("§4").setScore(0);
        player.setScoreboard(scoreboard);
    }

    /**
     * leer
     * name
     * name
     * leer
     * rang
     * rang
     * leer
     * server
     * server
     * leer
     */

}
