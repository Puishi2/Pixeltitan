package de.Puishi.TitanJump.countdown;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.deathmatch.PlayerManager;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 09.07.2020 / 14:00                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class DeathmatchCountdown {

    public int start = 10;
    protected static int task;

    public void startStartCountdown(Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                switch (start) {
                    case 10:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b10 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 5:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b5 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 4:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b4 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 3:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b3 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 2:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b2 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 1:
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b1 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 10, 10);
                        break;
                    case 0:
                        TitanJump.getInstance().setGameState(GameState.INGAME);
                        player.sendMessage(Data.getPrefix() + "Das Deathmatch beginnt §bjetzt§7!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        TitanJump.getInstance().setGameState(GameState.DEATHMATCH);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            PlayerManager.playerList.add(all.getUniqueId().toString());
                        }

                        stop();



                        break;

                    default:
                        break;
                }
                DeathmatchCountdown.this.start--;
            }
        },0, 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }

}
