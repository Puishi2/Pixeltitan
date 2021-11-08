package de.Puishi.TitanJump.countdown;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.perks.Perk_Manager;
import de.Puishi.TitanJump.team.TeamManager;
import de.Puishi.TitanJump.team.Teamauswahl_Listener;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 14.06.2020 / 19:37                                               *
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

    public int countdown = 30;
    protected static int start;
    public boolean isRunning;

    public void start(Player player) {
        start = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                isRunning = true;
                switch (countdown) {
                    case 30:
                    case 20:
                    case 15:
                    case 10:
                    case 5:
                    case 4:
                    case 3:
                    case 2:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel startet in §b" + countdown + " §bSekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel startet in §beiner Sekunde§7.");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 0:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Spiel startet §bjetzt§7.");
                        TitanJump.getInstance().setGameState(GameState.STARTING);
                        System.out.println(TitanJump.getInstance().getGameState());
                        player.spigot().playEffect(player.getLocation(), Effect.FIREWORKS_SPARK, 1, 1, 0.0F, 10.0F, 0.0F, 0.2F, 15, 10);

                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.getInventory().clear();
                            all.getInventory().setArmorContents(null);
                            if(Perk_Manager.perk.containsKey(all.getUniqueId().toString())) {
                                new Perk_Manager().setPerk(all);
                                all.getLocation().getWorld().setTime(6000);
                            }

                            new Teamauswahl_Listener().randomTeam(all);

                            if(TeamManager.rot.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("1"));
                            } else if(TeamManager.blau.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("2"));
                            } else if(TeamManager.gelb.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("3"));
                            } else if(TeamManager.grün.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("4"));
                            } else if(TeamManager.lila.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("5"));
                            } else if(TeamManager.pink.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("6"));
                            }  else if(TeamManager.hellgrün.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("7"));
                            } else if(TeamManager.hellblau.contains(all.getUniqueId().toString())) {
                                all.teleport(new LocationManager().getLocation("8"));
                            }
                        }

                        new StartCountdown().startStartCountdown(player);

                        isRunning = false;
                        stop();

                        break;

                    default:
                        break;
                }
                LobbyCountdown.this.countdown--;
            }
        }, 0, 20);
    }

    public void stop() {
        this.isRunning = false;
        Bukkit.getScheduler().cancelTask(start);
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public int getCountdown() {
        return countdown;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public static int getStart() {
        return start;
    }
}
