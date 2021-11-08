package de.Puishi.SkyWars.countdown;

import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.database.StatsManager;
import de.Puishi.SkyWars.kits.KitManager;
import de.Puishi.SkyWars.scoreboard.ScoreboardManager;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.GameState.GameStates;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.server.ServerState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 28.05.2020 / 01:15                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class LobbyCountdown {

    public static HashMap<Block, Location> chests = new HashMap<>();

    public int seconds = 30;
    public int task;

    private int spawnID;

    public boolean isRunning;

    public void start(final Player p) {

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {

                isRunning = true;

                switch (seconds) {

                    case 30:
                    case 20:
                    case 10:
                    case 5:
                    case 4:
                    case 3:
                    case 2:

                        Bukkit.broadcastMessage(Data.PREFIX + "Das Spiel startet in §2" + seconds + " §2Sekunden§7!");
                        p.playSound(p.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;

                    case 1:
                        Bukkit.broadcastMessage(Data.PREFIX + "Das Spiel startet in §2einer §2Sekunde§7!");
                        p.playSound(p.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;

                    case 0:

                        Bukkit.broadcastMessage(Data.PREFIX + "§7Das Spiel beginnt §2jetzt§7!");

                        Main.getInstance().setGameStates(GameStates.INGAME);
                        System.out.println(Main.getInstance().getGameStates());

                        Bukkit.getScheduler().cancelTask(task);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            StatsManager.addStats(p.getUniqueId().toString(), 1, "PLAYED");
                            p.setFoodLevel(20);
                            p.setHealth(20);
                            spawnID++;
                            all.teleport(Main.getInstance().getMapManager().loadLocation(Main.getInstance().getMap() + "." + spawnID));

                            if(!KitManager.kit.containsKey(all.getUniqueId().toString())) {
                                KitManager.setNoKit(all);
                            } else {
                                KitManager.setKit(all);
                            }

                            new ScoreboardManager().setIngameScoreboard(all);

                        }

                        Main.getInstance().getRefillCountdown().start(p);
                        CloudServer.getInstance().setServerState(ServerState.INGAME);

                        isRunning = false;

                        break;

                    default:
                        break;
                }
                seconds--;
            }

        }, 0, 20);
    }

    public void stop() {
        if(isRunning) {
            isRunning = false;
            Bukkit.getScheduler().cancelTask(task);
            Bukkit.broadcastMessage("§cDer Countdown wurde gestoppt!");
        }

    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
