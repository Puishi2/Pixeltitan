package de.Puishi.Teambesprechung.listener;

import de.Puishi.Teambesprechung.Teambesprechung;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.UUID;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 18:30                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerInventoryClick implements Listener {

    public static ArrayList<String> togglemovelist = new ArrayList<>();

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();

        if(e.getInventory() != null){
            if(e.getInventory().getName().equalsIgnoreCase("§8» §cTeambesprechung")){
                if(e.getCurrentItem().getType() == Material.SIGN){
                    if(ToggleChatListener.togglechat == true){
                        p.sendMessage(Teambesprechung.PREFIX + "Der Chat wurde deaktiviert!");
                        ToggleChatListener.togglechat = false;
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1,1);
                    }else {
                        ToggleChatListener.togglechat = true;
                        p.sendMessage(Teambesprechung.PREFIX + "Der Chat wurde aktiviert!");
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1,1);
                    }

                }else if(e.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
                    if(togglemovelist.contains(p.getName())){
                        togglemovelist.remove(p.getName());
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Es können sich nun alle Spieler wieder bewegen!");
                    }else {
                        togglemovelist.add(p.getName());
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Es kann sich nun kein Spieler mehr bewegen!");
                    }
                } else if(e.getCurrentItem().getType() == Material.FEATHER){
                    if(togglemovelist.contains(p.getName())){
                        togglemovelist.remove(p.getName());
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Es können sich nun alle Spieler wieder bewegen!");
                    }else {
                        togglemovelist.add(p.getName());
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Es kann sich nun kein Spieler mehr bewegen!");
                    }
                    if(ToggleChatListener.togglechat == true){
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Der Chat wurde deaktiviert!");
                        ToggleChatListener.togglechat = false;
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1,1);
                    }else {
                        ToggleChatListener.togglechat = true;
                        Bukkit.broadcastMessage(Teambesprechung.PREFIX + "Der Chat wurde aktiviert!");
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1,1);
                    }
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                }
            }
        }
    }
}
