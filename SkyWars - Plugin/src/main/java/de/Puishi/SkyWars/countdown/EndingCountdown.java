package de.Puishi.SkyWars.countdown;

import de.Puishi.SkyWars.GameState.GameStates;
import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.kits.KitManager;
import de.Puishi.SkyWars.utils.Data;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.dytanic.cloudnet.lib.server.ServerState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 06.06.2020 / 22:25                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class EndingCountdown {

    public int seconds = 10;
    public int task;

    public boolean isRunning;

    public void start(final Player p) {

        isRunning = true;

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            @Override
            public void run() {

                switch (seconds) {

                    case 10:
                    case 5:
                    case 4:
                    case 3:

                    case 2:

                        Bukkit.broadcastMessage(Data.PREFIX + "Der Server stoppt in §2" + seconds + " §7Sekunden!");
                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                        break;

                    case 1:
                        Bukkit.broadcastMessage(Data.PREFIX + "Der Server stoppt in §2einer §7Sekunde!");
                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                        break;

                    case 0:

                        Bukkit.broadcastMessage(Data.PREFIX + "§7Der Server stoppt §2jetzt§7!");

                        Bukkit.getScheduler().cancelTask(task);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.kickPlayer(Data.PREFIX + "§cDas Spiel ist vorbei!");
                        }

                        Bukkit.getServer().shutdown();

                        isRunning = false;
                        CloudServer.getInstance().setServerState(ServerState.OFFLINE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }

        }, 0, 20);
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
