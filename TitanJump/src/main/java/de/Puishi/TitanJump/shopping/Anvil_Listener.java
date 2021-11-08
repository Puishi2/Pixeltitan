package de.Puishi.TitanJump.shopping;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 04.07.2020 / 20:43                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Anvil_Listener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAmboss")) {
                    Inventory inventory = Bukkit.createInventory(null, InventoryType.ANVIL, "§8» §bAmboss");
                    event.getPlayer().openInventory(inventory);
                    event.getPlayer().spigot().playEffect(event.getPlayer().getLocation(), Effect.CLOUD, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);
                }
            }
        }
    }

}
