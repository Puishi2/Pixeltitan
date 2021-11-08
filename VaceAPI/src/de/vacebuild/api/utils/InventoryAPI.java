package de.vacebuild.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                this.inventory.setItem(slots, new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b")
                        .setData(id).build());
            }
        }

        return this;
    }

    public Inventory build() {
        return this.inventory;
    }

}
