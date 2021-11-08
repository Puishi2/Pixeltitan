package de.vacebuild.trial.utils;

import de.vacebuild.api.utils.InventoryAPI;
import de.vacebuild.api.utils.ItemManager;
import de.vacebuild.trial.main.Trialsystem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

    private Trialsystem plugin;
    private Inventory defaultInv;
    private Inventory confirmInv;

    public InventoryManager(Trialsystem plugin) {
        this.plugin = plugin;

        ItemStack glass = new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b").setData(7).build();

        this.defaultInv = new InventoryAPI("", 9)
                .setItem(new ItemManager(Material.COMPASS).setDisplayName("§8» §bFertige Welten").build(), 7)
                .setItem(new ItemManager(Material.DIAMOND).setDisplayName("§8» §bWelt bewerten").build(), 8).build();

        this.confirmInv = new InventoryAPI("", 9 * 3).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 18).setItem(glass, 19).setItem(glass, 20).setItem(glass, 21).setItem(glass, 22).setItem(glass, 23)
                .setItem(glass, 24).setItem(glass, 25).setItem(glass, 26)
                .setItem(new ItemManager(Material.STAINED_CLAY).setDisplayName("§8» §bFortfahren").setData(5).build(), 11)
                .setItem(new ItemManager(Material.STAINED_CLAY).setDisplayName("§8» §bAbbrechen").setData(14).build(), 15).build();
    }

    public void setInventory(Player player) {
        player.getInventory().setArmorContents(null);
        player.getInventory().setContents(this.defaultInv.getContents());
    }

    public void openConfirmInv(Player player, String name, Sound sound) {
        Inventory inventory = new InventoryAPI("§8» §b" + name, 9 * 3).build();

        inventory.setContents(this.confirmInv.getContents());

        player.openInventory(inventory);
        player.playSound(player.getLocation(), sound, 0.5F, 0.5F);
    }

}
