package es.Punikii.SummerWars.Managers;

import de.javaarray.pixeltitan.coinsapi.api.CoinsAPI;
import es.Punikii.SummerWars.SummerWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.HashMap;

public class ScoreboardManager implements Listener {

    private static HashMap<Scoreboard, Player> boards = new HashMap<>();

    public static void updater() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Scoreboard board : boards.keySet()) {
                    Player p = boards.get(board);
                    board.getTeam("online").setSuffix("§8● §7" + Bukkit.getOnlinePlayers().size());

                }
            }
        }.runTaskTimer(SummerWars.getInstance(), 0, 1);
    }

    public void createScoreboard(Player p) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("abcde", "abcde");

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§8* §6SummerWars §8*");

        Team OnlineSpieler = board.registerNewTeam("online");
        OnlineSpieler.setPrefix("§8");
        OnlineSpieler.setSuffix(" §8●" + Bukkit.getOnlinePlayers().size());
        OnlineSpieler.addEntry(ChatColor.GREEN.toString());

        obj.getScore(" ").setScore(9);
        obj.getScore("§8■ §7Rang").setScore(8);
        if (p.hasPermission("pixel.hadmin"))
            obj.getScore(" §8● §4HeadAdmin").setScore(7);
        else if (p.hasPermission("pixel.admin")) {
            obj.getScore(" §8● §4Admin").setScore(7);
        } else if (p.hasPermission("pixel.cadmin")) {
            obj.getScore(" §8● §4CAdmin").setScore(7);
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
        obj.getScore("§f").setScore(6);
        obj.getScore("§8■ §7Coins").setScore(5);
        obj.getScore(" §8● §7" + CoinsAPI.getCoins(p.getUniqueId().toString())).setScore(4);
        obj.getScore("§1").setScore(3);
        obj.getScore("§8■ §7Online").setScore(2);
        obj.getScore(ChatColor.GREEN.toString()).setScore(1);
        obj.getScore("").setScore(0);

        boards.put(board, p);

        p.setScoreboard(board);
    }
}