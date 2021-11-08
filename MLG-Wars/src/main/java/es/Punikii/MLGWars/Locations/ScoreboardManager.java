package es.Punikii.MLGWars.Locations;
import es.Punikii.MLGWars.Level.LevelManager;
import es.Punikii.MLGWars.Lives.Lives;
import es.Punikii.MLGWars.MLGWars;
import es.Punikii.MLGWars.gamestate.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class ScoreboardManager implements Listener {

    private static HashMap<Scoreboard, Player> boards = new HashMap<>();

    public void createScoreboard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("aaaa", "bbbb");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8» §cMLGWars §8«");


        obj.getScore("§1 ").setScore(12);
        obj.getScore("§8■ §7Name").setScore(11);
        obj.getScore(" §8● §c" + p.getName()).setScore(10);
        obj.getScore(" §a").setScore(9);
        obj.getScore("§8■ §7Rang").setScore(8);
        if (p.hasPermission("pixel.hadmin"))
            obj.getScore(" §8● §4Head-Admin").setScore(7);
        else if (p.hasPermission("pixel.admin")) {
            obj.getScore(" §8● §4Admin").setScore(7);
        } else if (p.hasPermission("pixel.cadmin")) {
            obj.getScore(" §8● §4C-Admin").setScore(7);
        } else if (p.hasPermission("pixel.srdev")) {
            obj.getScore(" §8● §bSrDev").setScore(7);
        } else if (p.hasPermission("pixel.dev")) {
            obj.getScore(" §8● §bDev").setScore(7);
        } else if (p.hasPermission("pixel.content")) {
            obj.getScore(" §8● §bContent").setScore(7);
        } else if (p.hasPermission("pixel.srmod")) {
            obj.getScore(" §8● §cSrMod").setScore(7);
        } else if (p.hasPermission("pixel.mod")) {
            obj.getScore(" §8● §cMod").setScore(7);
        } else if (p.hasPermission("pixel.sup")) {
            obj.getScore(" §8● §9Supporter").setScore(7);
        } else if (p.hasPermission("pixel.srbuilder")) {
            obj.getScore(" §8● §3SrBuilder").setScore(7);
        } else if (p.hasPermission("pixel.builder")) {
            obj.getScore(" §8● §3Builder").setScore(7);
        } else if (p.hasPermission("pixel.yt")) {
            obj.getScore(" §8● §5YouTuber").setScore(7);
        } else if (p.hasPermission("pixel.pixel")) {
            obj.getScore(" §8● §aPixel").setScore(7);
        } else if (p.hasPermission("pixel.premium")) {
            obj.getScore(" §8● §6Premium").setScore(7);
        } else {
            obj.getScore(" §8● §7Spieler").setScore(7);
        }
        obj.getScore("§5").setScore(6);
        obj.getScore("§8■ §7Coins").setScore(5);
        obj.getScore("§2").setScore(3);
        obj.getScore("§8■ §7Level").setScore(2);

        Team Coins = board.registerNewTeam("Coins");
        Coins.setPrefix("§8");
        if (es.Punikii.MLGWars.Coins.Coins.coins.containsKey(p))
            Coins.setSuffix(" §8● §c" + es.Punikii.MLGWars.Coins.Coins.coins.get(p));
        else
            Coins.setSuffix(" §8● §c0");
        Coins.addEntry("§c");
        obj.getScore("§c").setScore(4);

        Team Level = board.registerNewTeam("Level");
        Level.setPrefix("§8");
        if (LevelManager.level.containsKey(p))
            Level.setSuffix(" §8● §c" + LevelManager.level.get(p));
        else
            Level.setSuffix(" §8● §c---");
        Level.addEntry("§f");
        obj.getScore("§f").setScore(1);

        obj.getScore("§3").setScore(0);

        p.setScoreboard(board);

        boards.put(board, p);
    }

    public static void updater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard board : boards.keySet()) {
                    Player p = boards.get(board);

                    if(!MLGWars.getInstance().getGameStates().equals(GameStates.PVP)) {
                        if (es.Punikii.MLGWars.Coins.Coins.coins.containsKey(p))
                            board.getTeam("Coins").setSuffix(" §8● §c" + es.Punikii.MLGWars.Coins.Coins.coins.get(p));

                        if (LevelManager.level.containsKey(p))
                            board.getTeam("Level").setSuffix(" §8● §c" + LevelManager.level.get(p));
                    } else {
                        board.resetScores("§8■ §7Coins");
                        board.getObjective(DisplaySlot.SIDEBAR).getScore("§8■ §7Leben").setScore(5);
                        board.resetScores("§8■ §7Level");
                        board.getObjective(DisplaySlot.SIDEBAR).getScore("§8■ §7Am Leben").setScore(2);

                        board.getTeam("Coins").setSuffix(" §8● §c" + Lives.lives.get(p));
                        board.getTeam("Level").setSuffix(" §8● §c" + MLGWars.playlist.size());

                    }

                }
            }
        }.runTaskTimer(MLGWars.getInstance(), 0, 1);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        createScoreboard(event.getPlayer());
    }

}