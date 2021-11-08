package de.Puishi.Bauserver.wm;

import de.Puishi.Bauserver.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 21.06.2020 / 12:06                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class InventoryAPI {

    private Inventory inventory;

    public InventoryAPI(String name, int size) {
        this.inventory = Bukkit.getServer().createInventory(null, size, name);
    }

    public InventoryAPI(String name, InventoryType type) {
        this.inventory = Bukkit.getServer().createInventory(null, type, name);
    }

    public InventoryAPI addItem(ItemStack itemStack) {
        this.inventory.addItem(itemStack);

        return this;
    }

    public InventoryAPI setItem(ItemStack itemStack, int slot) {
        this.inventory.setItem(slot, itemStack);

        return this;
    }

    public InventoryAPI setContents(ItemStack[] contents) {
        this.inventory.setContents(contents);

        return this;
    }

    public InventoryAPI fill(int id) {
        for (int slots = 0; slots < this.inventory.getContents().length; slots++) {
            ItemStack glass = this.inventory.getItem(slots);

            if (glass == null || glass.getType() == Material.AIR) {
                this.inventory.setItem(slots, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§b")
                        .setDurability((short) id).toItemStack());
            }
        }

        return this;
    }

    public Inventory build() {
        return this.inventory;
    }

}
