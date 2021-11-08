package de.Puishi.TitanJump.countdown;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.gamestate.GameState;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 05.07.2020 / 10:31                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Shopping_Countdown implements Listener {

    public int start = 10;
    protected static int task;
    private boolean isDamage;

    public void startShoppingcountdown(Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TitanJump.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                switch (start) {
                    case 120:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b2 Minuten§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 60:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §beiner Minute§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 30:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b30 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 20:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b20 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 10:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b10 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 5:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b5 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 4:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b4 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 3:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b3 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 2:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b2 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §b1 Sekunden§7!");
                        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 10, 10);
                        break;
                    case 0:
                        Bukkit.broadcastMessage(Data.getPrefix() + "Das Deathmatch beginnt in §bjetzt§7!");
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        TitanJump.getInstance().setGameState(GameState.DEATHMATCHCOUNTDOWN);
                        for(Player all : Bukkit.getOnlinePlayers()) {
                            all.teleport(new LocationManager().getLocation("deathmatch"));
                            all.getLocation().getWorld().setTime(6000);
                            all.getInventory().remove(Material.SKULL_ITEM);
                            all.getInventory().remove(Material.ANVIL);
                            new DeathmatchCountdown().startStartCountdown(player);
                        }

                        stop();

                        break;

                    default:
                        break;
                }
                Shopping_Countdown.this.start--;
            }
        },0, 20);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(task);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if(isDamage == true) {
            event.setCancelled(true);
        }
    }

}
