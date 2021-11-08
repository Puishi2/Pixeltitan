package de.Puishi.Bauserver.wm;

import de.Puishi.Bauserver.utils.Base64;
import de.Puishi.Bauserver.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 21.06.2020 / 11:53                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class WM_Inventory {

    private Inventory wmInv;

    public void InventoryBuilder(Player player) {
        this.wmInv = Bukkit.createInventory(null, 9 * 4, "§8» §bWorldmanager");

        wmInv.setItem(10, new ItemBuilder(Material.GRASS).setName("§8» §bWelt erstellen").toItemStack());
        wmInv.setItem(12, new ItemBuilder(Material.COMPASS).setName("§8» §bTeleportieren").toItemStack());
        wmInv.setItem(14, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf")).setName("§8» §bWelt importieren").toItemStack());
        wmInv.setItem(16, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10")).setName("§8» §bWelt unloaden").toItemStack());
        wmInv.setItem(20, new ItemBuilder(Material.BARRIER).setName("§8» §bWelt löschen").toItemStack());
        wmInv.setItem(22, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("§8» §bStatus").toItemStack());
        wmInv.setItem(24, new ItemBuilder(Material.PAPER).setName("§8» §bWhitelist").toItemStack());
        player.updateInventory();

    }

    public void openWM(Player player) {
        player.openInventory(this.wmInv);
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5F, 0.5F);
    }

}
