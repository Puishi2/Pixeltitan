package de.Puishi.TitanJump.countdown;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 11.07.2020 / 10:07                                               *
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

    public int start = 10;
    protected static int task;

    public void startStartCountdown(Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                switch (start) {
                    case 10:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in 10 Sekunden!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 5:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in 5 Sekunden!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 4:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in 4 Sekunden!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 3:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in 3 Sekunden!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 2:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in 2 Sekunden!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt in einer Sekunde!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 0:
                        TitanJump.getInstance().setGameState(GameState.INGAME);
                        Bukkit.broadcastMessage(Data.getPrefix() + "Der Server stoppt jetzt!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        Bukkit.getServer().shutdown();

                        break;

                    default:
                        break;
                }
                EndingCountdown.this.start--;
            }
        },0, 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }


}
