package de.Puishi.SkyWars.countdown;

import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.chestloot.ChestLoot;
import de.Puishi.SkyWars.utils.ActionBar;
import de.Puishi.SkyWars.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Created by Luca on 08.06.2020.
 * Project: PixelTitan
 * © Copyright by JavaArray | Luca K.
 */

public class RefillCountdown {

    private int refilltime = 130;
    private int task;

    public void start(final Player player) {
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {

                switch (refilltime) {

                    case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Die Kisten werden in §2" + refilltime + " §7Sekunden nach gefüllt!");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Die Kisten werden in §2einer §7Sekunden nach gefüllt!");
                        break;
                    case 0:
                        Bukkit.broadcastMessage(Data.PREFIX + "§7Die Kisten wurden nach gefüllt!");

                        for(Location locations : ChestLoot.chestloc) {
                            locations.getBlock().setType(Material.CHEST);
                        }

                        Bukkit.getScheduler().cancelTask(task);
                        break;

                    default:
                        break;

                }
                refilltime--;

                for(Player all : Bukkit.getOnlinePlayers()) {
                    ActionBar.setActionBar(all, Data.PREFIX + "§7Die Kisten füllen sich in §2" + refilltime + " Sekunden §7neu!");
                    if(refilltime == -1 || refilltime == 0) {
                        ActionBar.setActionBar(all, Data.PREFIX + "§7Die Kisten wurden nachgefüllt!") ;
                    }
                }

            }
        }, 0, 20);
    }
}
