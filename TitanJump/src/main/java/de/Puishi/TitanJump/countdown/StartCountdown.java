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
 *    Erstellt: 23.06.2020 / 11:13                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class StartCountdown {

    public int start = 5;
    protected static int task;

    public void startStartCountdown(Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                switch (start) {
                    case 5:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt in §b5 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 4:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt in §b4 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 3:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt in §b3 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 2:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt in §b2 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt in §b1 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 0:
                        TitanJump.getInstance().setGameState(GameState.INGAME);
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel beginnt §bjetzt§7!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            new IngameCountdown().start(all);
                        }

                        stop();

                    break;

                    default:
                        break;
                }
                StartCountdown.this.start--;
            }
        },0, 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }

}
