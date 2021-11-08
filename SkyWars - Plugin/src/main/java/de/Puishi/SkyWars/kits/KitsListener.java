package de.Puishi.SkyWars.kits;

import de.Puishi.SkyWars.Main;
import de.Puishi.SkyWars.database.KitDatabaseManager;
import de.Puishi.SkyWars.utils.Data;
import de.Puishi.SkyWars.utils.ItemBuilder;
import org.bukkit.Bukkit;
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
 *    Erstellt: 24.05.2020 / 20:46                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class KitsListener implements Listener {

    public static boolean isOwned;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().getDisplayName() != null) {
                    if (event.getItem().getType() == Material.SKULL_ITEM && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aKits")) {
                        final Inventory inv = Bukkit.createInventory(null, 54, "§8» §aKits");
                        player.openInventory(inv);
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
                                inv.setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());

                                inv.setItem(11, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§8» §6Titan-Kit §7(§cStandart§7)").addLoreLine("§8» §7Eisenboots, Eisenschwert, Eisenaxt, Eisenpickaxe, 32 Stein").toItemStack());
                                inv.setItem(13, new ItemBuilder(Material.GLOWSTONE_DUST).setName("§8» §eSpotter-Kit §7(§c1000 Coins§7)").addLoreLine("§8» §7Spotter, Diamantaxt").toItemStack());
                                inv.setItem(15, new ItemBuilder(Material.EXP_BOTTLE).setName("§8» §aPixel-Kit §7(§c1000 Coins§7)").addLoreLine("§8» §7Eisenaxt, 32 EXP-Bottles, Verzauberungstisch, Diamant-Brustplatte").toItemStack());
                                inv.setItem(21, new ItemBuilder(Material.EYE_OF_ENDER).setName("§8» §2TP-Kit §7(§c1200 Coins§7)").addLoreLine("§8» §7 2 Enderperlen, Eisengitter-Brustpanzer").toItemStack());
                                inv.setItem(23, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §bAssasine-Kit §7(§c1500 Coins§7)").addLoreLine("§8» §7 1 Diamantschwert, 1 Goldapfel").toItemStack());
                                inv.setItem(29, new ItemBuilder(Material.IRON_PICKAXE).setName("§8» §dBuild-Kit §7(§c1000 Coins§7)").addLoreLine("§8» §7 1 Eisenpickaxe, 3 Stacks Ziegelstein, 1 Goldboots").toItemStack());
                                inv.setItem(39, new ItemBuilder(Material.WATER_BUCKET).setName("§8» §9MLG-Kit §7(§c500 Coins§7)").addLoreLine("§8» §7 1 Wassereimer, 5 Cobwebs, 5 Slimeblocks, 8 TNT, 32 Holz").toItemStack());

                                inv.setItem(31, new ItemBuilder(Material.BARRIER).setName("§cFuture").toItemStack());
                                inv.setItem(33, new ItemBuilder(Material.BARRIER).setName("§cFuture").toItemStack());
                                inv.setItem(41, new ItemBuilder(Material.BARRIER).setName("§cFuture").toItemStack());
                            }
                        }.runTaskLater(Main.getInstance(), 1);
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase("§8» §aKits")) {
        if (event.getCurrentItem().getItemMeta().getDisplayName() != null) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eSpotter-Kit §7(§c1000 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Spotter", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §eSpotter-Kit");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aPixel-Kit §7(§c1000 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Pixel", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §aPixel-Kit");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §2TP-Kit §7(§c1200 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Tp", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §2TP-Kit");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAssasine-Kit §7(§c1500 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Assasine", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §bAssasine-Kit");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §dBuild-Kit §7(§c1000 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Build", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §dBuild-Kit");
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §9MLG-Kit §7(§c500 Coins§7)")) {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Mlg", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §9MLG-Kit");
            } else {
                KitDatabaseManager.setKit(player.getUniqueId().toString(), "Titan", "KIT");
                player.sendMessage(Data.PREFIX + "Du hast das Kit gekauft!");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                KitManager.kit.put(player.getUniqueId().toString(), "§8» §6Titan-Kit");
            }
        }
        }
    }
}
