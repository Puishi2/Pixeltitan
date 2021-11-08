package de.Puishi.BedWars.utils;

import de.Puishi.BedWars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.06.2020 / 21:51                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Spawner {

    private static int run;
    private static ArrayList<Location> bronze = new ArrayList<>();
    private static ArrayList<Location> eisen = new ArrayList<>();
    private static ArrayList<Location> gold = new ArrayList<>();
    private static int eisenspawner;
    private static int goldspawner;

    public static void start(){
        run = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                eisenspawner--;
                if(eisenspawner == 0){
                    eisenspawner = 24;
                    for(int i = 0; i < eisen.size() ; i++){
                        ItemStack itemStack = new ItemStack(Material.IRON_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§7Eisen");
                        itemStack.setItemMeta(itemMeta);
                        Bukkit.getWorld(eisen.get(i).getWorld().getName()).dropItem(eisen.get(i), itemStack);
                        Bukkit.getWorld(eisen.get(i).getWorld().getName()).playEffect(eisen.get(i), Effect.CLOUD, 1);
                    }
                }
                goldspawner--;
                }

        },10, 10);
    }

}
