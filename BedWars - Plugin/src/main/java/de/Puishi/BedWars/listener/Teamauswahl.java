package de.Puishi.BedWars.listener;

import de.Puishi.BedWars.Main;
import de.Puishi.BedWars.utils.Data;
import de.Puishi.BedWars.utils.ItemBuilder;
import de.Puishi.BedWars.utils.Teammanager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 17.05.2020 / 15:27                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teamauswahl implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getItem().getType() != null){
            if(e.getItem().getType() == Material.MAGMA_CREAM){
                if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    Inventory inv = Bukkit.createInventory(null, 9, "§8» §3Teamauswahl");
                    e.getPlayer().openInventory(inv);
                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.LEVEL_UP, 10, 10);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            inv.setItem(2, new ItemBuilder(Material.INK_SACK).setDurability((short) 6).setName("§8» §3Blau").toItemStack());
                            inv.setItem(6, new ItemBuilder(Material.INK_SACK).setDurability((short) 1).setName("§8» §cRot").toItemStack());
                        }
                    }.runTaskLater(Main.getInstance(), 1);
                }
            }
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory() != null){
            if(e.getInventory().getName().equalsIgnoreCase("§8» §3Teamauswahl")){
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cRot")){
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                    p.sendMessage(Data.PREFIX + "Du bist in Team §cRot§7.");
                    Teammanager.teamrot.add(p);
                }else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §3Blau")){
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                    p.sendMessage(Data.PREFIX + "Du bist in Team §3Blau§7.");
                    Teammanager.teamblau.add(p);
                }
            }
        }
    }

}
