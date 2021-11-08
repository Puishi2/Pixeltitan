package de.Puishi.BedWars.countdown;

import de.Puishi.BedWars.Main;
import de.Puishi.BedWars.gamestate.GameState;
import de.Puishi.BedWars.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.06.2020 / 20:15                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerCountdown {

    static int levelcountdown = 30;
    static int countdown = 30;
    protected static int start;
    protected static int go;
    private int spawnID;;

    public void start(Player player) {
        start = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch (countdown) {
                    case 30:
                    case 20:
                    case 15:
                    case 10:
                    case 5:
                    case 3:
                    case 2:
                        Bukkit.broadcastMessage(Data.PREFIX + "Das Spiel startet in §3" + countdown + " §3Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.PREFIX + "Das Spiel startet in §3einer Sekunde§7.");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 0:
                        Bukkit.broadcastMessage(Data.PREFIX + "Das Spiel startet §3jetzt§7.");
                        Main.getInstance().setGameState(GameState.INGAME);
                        player.getInventory().clear();
                        player.getInventory().setArmorContents(null);
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        for(Player all : Bukkit.getOnlinePlayers()){
                            spawnID++;
                            all.teleport(Main.getInstance().getLocationAPI().loadLocation(Main.getInstance().getMap() + "." + spawnID));
                        }

                        break;
                    default:
                        break;
                }
                countdown--;
            }
        }, 0, 20);
    }

}
