package de.Puishi.Teambesprechung.listener;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.commands.Command_Next;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.text.SimpleDateFormat;
import java.util.Date;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 13.05.2020 / 16:08                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Scoreboard implements Listener {

    public void setScoreboard(Player player){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String original = simpleDateFormat.format(new Date());
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("sfs", "sdf");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8» §cTeambesprechung §8«");

        objective.getScore("§1").setScore(9);
        objective.getScore("  §8■ §7Name").setScore(8);
        objective.getScore("    §8● §a" + player.getName()).setScore(7);
        objective.getScore("§2").setScore(6);
        objective.getScore("  §8■ §7Datum").setScore(5);
        objective.getScore("    §8● §a" + original).setScore(4);
        objective.getScore("§3").setScore(3);
        objective.getScore("  §8■ §7Nächstes Meeting").setScore(2);
        objective.getScore("    §8● §a" + Command_Next.fb.getString("Datum")).setScore(1);
        objective.getScore("§4").setScore(0);


        player.setScoreboard(scoreboard);
    }

    /*
    frei
    name
    name
    frei
    datum
    datum
    frei
     */
}
