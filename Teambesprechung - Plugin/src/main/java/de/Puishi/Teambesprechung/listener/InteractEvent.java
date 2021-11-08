package de.Puishi.Teambesprechung.listener;

import de.Puishi.Teambesprechung.Teambesprechung;
import de.Puishi.Teambesprechung.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 12.05.2020 / 16:23                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class InteractEvent implements Listener {

    public Teambesprechung plugin;

    public InteractEvent(Teambesprechung plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem() != null){
            if(event.getItem().getType() == Material.PAPER){
                Inventory inv = Bukkit.createInventory(null, 9, "§8» §cTeambesprechung");
                player.openInventory(inv);
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inv.setItem(4, new ItemBuilder(Material.FEATHER).setName("§8» §cAlles aktivieren").addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().toItemStack());

                        inv.setItem(1, new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED).setName("§8» §cNicht bewegen").addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().toItemStack());

                        inv.setItem(7, new ItemBuilder(Material.SIGN).setName("§8» §cDeaktiviere den Chat").addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().toItemStack());
                    }
                }.runTaskLater(plugin, 1);

            }
        }
    }
}
