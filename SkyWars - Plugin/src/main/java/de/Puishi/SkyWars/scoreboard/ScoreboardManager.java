package de.Puishi.SkyWars.scoreboard;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.kits.KitManager;
import de.Puishi.SkyWars.utils.SpecUtils;
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
 *    Erstellt: 29.05.2020 / 00:43                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class ScoreboardManager {

    private static final HashMap<Scoreboard, Player> board = new HashMap<>();

    public void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8* §2SkyWars §8*");
        objective.getScore("§1").setScore(9);
        objective.getScore("§8■ §7Name").setScore(8);
        objective.getScore("§8● §a" + player.getName()).setScore(7);
        objective.getScore("§2").setScore(6);
        objective.getScore("§8■ §7Map").setScore(5);
        objective.getScore("§9").setScore(3);
        objective.getScore("§8■ §7Aktuelles Kit").setScore(2);
        objective.getScore("§4").setScore(0);

        Team team = scoreboard.registerNewTeam("x4");
        team.setPrefix("§8● §2");
        team.setSuffix(Main.getInstance().getMap());
        team.addEntry("§6");
        objective.getScore("§6").setScore(4);

        Team team1 = scoreboard.registerNewTeam("x1");
        team1.setPrefix("§8●");
        if (!KitManager.kit.containsKey(player.getUniqueId().toString())) {
            team1.setSuffix(" §c✗");
        } else {
            team1.setSuffix(KitManager.kit.get(player.getUniqueId().toString()).replace("§8»", ""));
        }
        team1.addEntry("§3");
        objective.getScore("§3").setScore(1);

        board.put(scoreboard, player);
        player.setScoreboard(scoreboard);

    }

    public void setIngameScoreboard(Player player) {

        board.clear();

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8* §2SkyWars §8*");
        objective.getScore("§1").setScore(9);
        objective.getScore("§8■ §7Aktuelles Kit").setScore(8);
        if (!KitManager.kit.containsKey(player.getUniqueId().toString())) {
            objective.getScore("§8● §c✗").setScore(7);
        } else {
            objective.getScore("§8● §a" + KitManager.kit.get(player.getUniqueId().toString()).replace("§8»", "")).setScore(7);
        }
        objective.getScore("§2").setScore(6);
        objective.getScore("§8■ §7Map").setScore(5);
        objective.getScore("§9").setScore(3);
        objective.getScore("§8■ §7Spieler").setScore(2);
        objective.getScore("§d").setScore(0);

        Team team = scoreboard.registerNewTeam("x6");
        team.setPrefix("§8● §2");
        team.setSuffix("§2" + Main.getInstance().getMap());
        team.addEntry("§4");
        objective.getScore("§4").setScore(4);

        Team team1 = scoreboard.registerNewTeam("x8");
        team1.setPrefix("§8● §2");
        team1.setSuffix("§2" + SpecUtils.playerlist.size());
        team1.addEntry("§6");
        objective.getScore("§6").setScore(1);

        board.put(scoreboard, player);
        player.setScoreboard(scoreboard);

    }

    public void update() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(Main.getInstance().getGameStates() == GameStates.LOBBY) {
                    for (Scoreboard scoreboard : board.keySet()) {
                        scoreboard.getTeam("x4").setSuffix(Main.getInstance().getMap());
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

    public void updateKit() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard scoreboard : board.keySet()) {
                    Player player = board.get(scoreboard);
                    if(Main.getInstance().getGameStates() == GameStates.LOBBY) {
                        if (!KitManager.kit.containsKey(player.getUniqueId().toString())) {
                            scoreboard.getTeam("x1").setSuffix(" §c✗");
                        } else {
                            scoreboard.getTeam("x1").setSuffix(KitManager.kit.get(player.getUniqueId().toString()).replace("§8»", ""));
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

    public void updatePlayer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(Main.getInstance().getGameStates() == GameStates.INGAME) {
                    for (Scoreboard scoreboard : board.keySet()) {
                        scoreboard.getTeam("x8").setSuffix("§2" + SpecUtils.playerlist.size());
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 1);
    }

}
