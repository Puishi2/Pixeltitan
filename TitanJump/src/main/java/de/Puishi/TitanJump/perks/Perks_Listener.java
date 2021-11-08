package de.Puishi.TitanJump.perks;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.utils.Base64;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 02.07.2020 / 15:14                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Perks_Listener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §2Perks")) {
                    Inventory inventory = Bukkit.createInventory(null, 54, "§8» §2Perks");
                    player.openInventory(inventory);

                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
                    player.spigot().playEffect(player.getLocation(), Effect.CLOUD, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            inventory.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());
                            inventory.setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 3).setName(" ").toItemStack());

                            inventory.setItem(20, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §aJump-Perk").toItemStack());
                            inventory.setItem(22, new ItemBuilder(Material.EMERALD).setName("§8» §eRettungs-Perk").toItemStack());
                            inventory.setItem(24, new ItemBuilder(Material.NETHER_STAR).setName("§8» §fFreeze-Perk").toItemStack());

                            inventory.setItem(30, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cKommt bald").toItemStack());
                            inventory.setItem(32, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cKommt bald").toItemStack());

                        }
                    }.runTaskLater(TitanJump.getInstance(), 1);
                }
            }
        }
    }

    @EventHandler
    public void onInvCLick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equalsIgnoreCase("§8» §2Perks")) {
            if(event.getCurrentItem().getType() == Material.BLAZE_ROD) {
                Perk_Manager.perk.put(player.getUniqueId().toString(), "§8» §aJump-Perk");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                player.sendMessage(Data.getPrefix() + "Du hast das §bJump-Perk §7ausgewählt!");

            } else if(event.getCurrentItem().getType() == Material.EMERALD) {
                Perk_Manager.perk.put(player.getUniqueId().toString(), "§8» §eRettungs-Perk");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                player.sendMessage(Data.getPrefix() + "Du hast das §bRettungs-Perk §7ausgewählt!");

            } else if(event.getCurrentItem().getType() == Material.NETHER_STAR) {
                Perk_Manager.perk.put(player.getUniqueId().toString(), "§8» §fFreeze-Perk");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                player.sendMessage(Data.getPrefix() + "Du hast das §bFreeze-Perk §7ausgewählt!");
            }
        }
    }

}
