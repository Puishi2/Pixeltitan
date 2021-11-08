package de.Puishi.TitanJump.countdown;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.shopping.Shopping_Inventory;
import de.Puishi.TitanJump.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 26.06.2020 / 08:43                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class IngameCountdown {

    public int secounds = 300;
    protected static int task;
    public boolean isRunning;

    public void start(Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                isRunning = true;
                switch (secounds) {
                    case 300:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b5 Minuten§7!");
                        break;

                    case 240:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b4 Minuten§7!");
                        break;

                    case 180:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b3 Minuten§7!");
                        break;

                    case 120:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b2 Minuten§7!");
                        break;

                    case 60:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b1 Minuten§7!");
                        break;

                    case 30:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b30 Sekunden§7!");
                        break;

                    case 15:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b15 Sekunden!§7!");
                        break;

                    case 10:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b10 Sekunden!§7!");
                        break;

                    case 3:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b3 Sekunden!§7!");
                        break;

                    case 2:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §b2 Sekunden!§7!");
                        break;

                    case 1:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet in §beiner Sekunde§7!");
                        break;
                    case 0:
                        player.sendMessage(Data.getPrefix() + "Das Spiel endet §bjetzt§7!");
                        TitanJump.getInstance().setGameState(GameState.SHOPPING);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            new Shopping_Countdown().startShoppingcountdown(player);
                            new Shopping_Inventory().setInventory(all);
                        }
                        isRunning = false;
                        stop();

                        break;
                    default:
                        break;
                }
                secounds--;
            }
        },0, 20);
    }

    public int getSecounds() {
        return secounds;
    }

    public void setSecounds(int secounds) {
        this.secounds = secounds;
    }

    public void stop() {
        this.isRunning = false;
        Bukkit.getScheduler().cancelTask(task);
    }
}
