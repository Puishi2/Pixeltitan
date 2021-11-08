package de.Puishi.TitanJump.shopping;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.points.PointsAPI;
import de.Puishi.TitanJump.utils.ActionBar;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
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
 *    Erstellt: 04.07.2020 / 20:00                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Shop_Listener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bShop")) {
                    Inventory inventory = Bukkit.createInventory(null, 9, "§8» §bShop");
                    player.openInventory(inventory);
                    player.spigot().playEffect(player.getLocation(), Effect.CLOUD, 1, 1, 0.0F, 0.0F, 0.0F, 0.2F, 32, 15);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            inventory.setItem(2, new ItemBuilder(Material.IRON_SWORD).setName("§8» §bRüstungen und Waffen").toItemStack());
                            inventory.setItem(4, new ItemBuilder(Material.GOLDEN_APPLE).setName("§8» §bSonstiges").toItemStack());
                            inventory.setItem(6, new ItemBuilder(Material.BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().setName("§8» §bVerzauberungen").toItemStack());
                        }
                    }.runTaskLater(TitanJump.getInstance(), 1);
                }
            }
        }
    }

    @EventHandler
    public void onInvCLick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equalsIgnoreCase("§8» §bShop")) {
            if(event.getCurrentItem().getType() == Material.IRON_SWORD) {
                Inventory inventory = Bukkit.createInventory(null, 9*4, "§8» §bRüstungen");
                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inventory.setItem(0, new ItemBuilder(Material.DIAMOND_HELMET).setName("§8» §bDiahelm §8- §c30 Points").toItemStack());
                        inventory.setItem(1, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§8» §bDiabrustpanzer §8- §c50 Points").toItemStack());
                        inventory.setItem(2, new ItemBuilder(Material.DIAMOND_LEGGINGS).setName("§8» §bDiahose §8- §c45 Points").toItemStack());
                        inventory.setItem(3, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§8» §bDiaschuhe §8- §c30 Points").toItemStack());

                        inventory.setItem(9, new ItemBuilder(Material.IRON_HELMET).setName("§8» §bEisenhelm §8- §c20 Points").toItemStack());
                        inventory.setItem(10, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§8» §bEisenbrustpanzer §8- §c35 Points").toItemStack());
                        inventory.setItem(11, new ItemBuilder(Material.IRON_LEGGINGS).setName("§8» §bEisenhose §8- §c 25 Points").toItemStack());
                        inventory.setItem(12, new ItemBuilder(Material.IRON_BOOTS).setName("§8» §bEisenschuhe §8- §c20 Points").toItemStack());

                        inventory.setItem(18, new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§8» §bKettenhelm §8- §c10 Points").toItemStack());
                        inventory.setItem(19, new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§8» §bKettenbrustpanzer §8- §c30 Points").toItemStack());
                        inventory.setItem(20, new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setName("§8» §bKettenhose §8- §c20 Points").toItemStack());
                        inventory.setItem(21, new ItemBuilder(Material.CHAINMAIL_BOOTS).setName("§8» §bKettenschuhe §8- §c10 Points").toItemStack());

                        inventory.setItem(27, new ItemBuilder(Material.LEATHER_HELMET).setName("§8» §bLederhelm §8- §c5 Points").toItemStack());
                        inventory.setItem(28, new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§8» §bLederbrustpanzer §8- §c7 Points").toItemStack());
                        inventory.setItem(29, new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§8» §bLederhose §8- §c6 Points").toItemStack());
                        inventory.setItem(30, new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §bLederschuhe §8- §c5 Points").toItemStack());

                        inventory.setItem(14, new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §bDiaschwert §8- §c50 Points").toItemStack());
                        inventory.setItem(15, new ItemBuilder(Material.IRON_SWORD).setName("§8» §bEisenschwert §8- §c40 Points").toItemStack());
                        inventory.setItem(16, new ItemBuilder(Material.STONE_SWORD).setName("§8» §bSteinschwer §8- §c30 Points").toItemStack());
                        inventory.setItem(17, new ItemBuilder(Material.WOOD_SWORD).setName("§8» §bHolzschwert §8- §c20 Points").toItemStack());
                        inventory.setItem(23, new ItemBuilder(Material.GOLD_SWORD).setName("§8» §bGoldschwert §8- §c20 Points").toItemStack());
                        inventory.setItem(24, new ItemBuilder(Material.DIAMOND_AXE).setName("§8» §bDiaaxt §8- §c35 Points").toItemStack());
                        inventory.setItem(25, new ItemBuilder(Material.IRON_AXE).setName("§8» §bEisenaxt §8- §c25 Points").toItemStack());
                        inventory.setItem(26, new ItemBuilder(Material.BOW).setName("§8» §bBogen §8- §c20 Points").toItemStack());

                    }
                }.runTaskLater(TitanJump.getInstance(), 1);

            } else if(event.getCurrentItem().getType() == Material.GOLDEN_APPLE) {
                Inventory inventory = Bukkit.createInventory(null, 9, "§8» §bSonstiges");
                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inventory.setItem(0, new ItemBuilder(Material.FISHING_ROD).setName("§8» §bAngel §8- §c20 Points").toItemStack());
                        inventory.setItem(1, new ItemBuilder(Material.ARROW).setName("§8» §bPfeil(10) §8- §c10 Points").toItemStack());
                        inventory.setItem(2, new ItemBuilder(Material.WATER_BUCKET).setName("§8» §bWassereimer §8- §c10 Points").toItemStack());
                        inventory.setItem(3, new ItemBuilder(Material.LAVA_BUCKET).setName("§8» §bLavaeimer §8- §c20 Points").toItemStack());
                        inventory.setItem(4, new ItemBuilder(Material.GOLDEN_APPLE).setName("§8» §bGoldapfel §8- §c20 Points").toItemStack());
                        inventory.setItem(5, new ItemBuilder(Material.GOLDEN_APPLE).setDurability((short) 1).setName("§8» §bOP-Goldapfel §8- §c60 Points").toItemStack());
                        inventory.setItem(6, new ItemBuilder(Material.CAKE).setName("§8» §bKuchen §8- §c5 Points").toItemStack());
                        inventory.setItem(7, new ItemBuilder(Material.COOKED_BEEF).setName("§8» §bFleisch §8- §c1 Point").toItemStack());
                        inventory.setItem(8, new ItemBuilder(Material.LAPIS_ORE).setName("§8» §bLapis(64) §8- §c10 Points").toItemStack());

                    }
                }.runTaskLater(TitanJump.getInstance(), 1);

            } else if(event.getCurrentItem().getType() == Material.BOOK) {
                Inventory inventory = Bukkit.createInventory(null, 9, "§8» §bVerzauberungen");
                player.openInventory(inventory);
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        inventory.setItem(2, new ItemBuilder(Material.BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().setName("§8» §b10 Level §8- §c10 Points").toItemStack());
                        inventory.setItem(4, new ItemBuilder(Material.BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().setName("§8» §b20 Level §8- §c20 Points").toItemStack());
                        inventory.setItem(6, new ItemBuilder(Material.BOOK).addEnchant(Enchantment.ARROW_DAMAGE, 1).setFlags().setName("§8» §b30 Level §8- §c30 Points").toItemStack());
                    }
                }.runTaskLater(TitanJump.getInstance(), 1);
            }
        }
    }

    @EventHandler
    public void onInvClick1(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equalsIgnoreCase("§8» §bVerzauberungen")) {
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8» §b10 Level §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.setLevel(10);
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §b20 Level §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.setLevel(20);
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §b30 Level §8- §c30 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.setLevel(30);
                        PointsAPI.removePoints(player, 30);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                default:
                    break;
            }
        } else if(event.getInventory().getName().equalsIgnoreCase("§8» §bRüstungen")) {
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8» §bDiahelm §8- §c30 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_HELMET).setName("§8» §bDiahelm").toItemStack());
                        PointsAPI.removePoints(player, 30);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bDiabrustpanzer §8- §c50 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§8» §bDiabrustpanzer").toItemStack());
                        PointsAPI.removePoints(player, 50);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bDiahose §8- §c45 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_LEGGINGS).setName("§8» §bDiahose").toItemStack());
                        PointsAPI.removePoints(player, 45);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }

                    break;

                case "§8» §bDiaschuhe §8- §c30 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_BOOTS).setName("§8» §bDiaschuhe").toItemStack());
                        PointsAPI.removePoints(player, 30);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }

                    break;

                case "§8» §bEisenhelm §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_HELMET).setName("§8» §bEisenhelm").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bEisenbrustpanzer §8- §c35 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_CHESTPLATE).setName("§8» §bEisenbrustpanzer").toItemStack());
                        PointsAPI.removePoints(player, 35);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bEisenhose §8- §c 25 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_LEGGINGS).setName("§8» §bEisenhose").toItemStack());
                        PointsAPI.removePoints(player, 25);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }

                    break;

                case "§8» §bEisenschuhe §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_BOOTS).setName("§8» §Eisenschuhe").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bKettenhelm §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§8» §bKettenhelm").toItemStack());
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bKettenbrustpanzer §8- §c30 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setName("§8» §bKettenbrustpanzer").toItemStack());
                        PointsAPI.removePoints(player, 30);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bKettenhose §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setName("§8» §bKettenhose").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }

                    break;

                case "§8» §bKettenschuhe §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.CHAINMAIL_BOOTS).setName("§8» §bKettenschuhe").toItemStack());
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bLederhelm §8- §c5 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LEATHER_HELMET).setName("§8» §bLederhelm").toItemStack());
                        PointsAPI.removePoints(player, 5);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bLederbrustpanzer §8- §c7 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LEATHER_CHESTPLATE).setName("§8» §bLederbrustpanzer").toItemStack());
                        PointsAPI.removePoints(player, 7);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bLederhose §8- §c6 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LEATHER_LEGGINGS).setName("§8» §bLederhose").toItemStack());
                        PointsAPI.removePoints(player, 6);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bLederschuhe §8- §c5 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LEATHER_BOOTS).setName("§8» §bLederschuhe").toItemStack());
                        PointsAPI.removePoints(player, 5);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bDiaschwert §8- §c50 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("§8» §bDiaschwert").toItemStack());
                        PointsAPI.removePoints(player, 50);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bEisenschwert §8- §c40 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_SWORD).setName("§8» §bEisenschwert").toItemStack());
                        PointsAPI.removePoints(player, 40);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bSteinschwer §8- §c30 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.STONE_SWORD).setName("§8» §vSteinschwert").toItemStack());
                        PointsAPI.removePoints(player, 30);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bHolzschwert §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.WOOD_SWORD).setName("§8» §bHolzschwert").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bGoldschwert §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.GOLD_SWORD).setName("§8» §bGoldschwert").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bDiaaxt §8- §c35 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.DIAMOND_AXE).setName("§8» §bDiaaxt").toItemStack());
                        PointsAPI.removePoints(player, 35);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bEisenaxt §8- §c25 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.IRON_AXE).setName("§8» §bEisenaxt").toItemStack());
                        PointsAPI.removePoints(player, 25);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

                case "§8» §bBogen §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.BOW).setName("§8» §bBogen").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                default:
                    break;
            }
        } else if(event.getInventory().getName().equalsIgnoreCase("§8» §bSonstiges")) {
            switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                case "§8» §bAngel §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.FISHING_ROD).setName("§8» §bAngel").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bPfeil(10) §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.ARROW).setAmount(10).setName("§8» §bPfeil").toItemStack());
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bWassereimer §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.WATER_BUCKET).setName("§8» §bWassereimer").toItemStack());
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bLavaeimer §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LAVA_BUCKET).setName("§8» §bLavaeimer").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bGoldapfel §8- §c20 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE).setName("§8» §bGoldapfel").toItemStack());
                        PointsAPI.removePoints(player, 20);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bOP-Goldapfel §8- §c60 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_APPLE).setDurability((short) 1).setName("§8» §bOP-Goldapfel").toItemStack());
                        PointsAPI.removePoints(player, 60);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bKuchen §8- §c5 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.CAKE).setName("§8» §bKuchen").toItemStack());
                        PointsAPI.removePoints(player, 5);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bFleisch §8- §c1 Point":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.COOKED_BEEF).setName("§8» §bFleisch").toItemStack());
                        PointsAPI.removePoints(player, 1);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;
                case "§8» §bLapis(64) §8- §c10 Points":
                    if(PointsAPI.getPoints(player) >= 0) {
                        player.getInventory().addItem(new ItemBuilder(Material.LAPIS_ORE).setAmount(64).setName("§8» §bLapis").toItemStack());
                        PointsAPI.removePoints(player, 10);
                        ActionBar.setActionBar(player, "§8» §bPoints §8: §b" + PointsAPI.getPoints(player));
                    } else {
                        player.sendMessage(Data.getPrefix() + "§cDu hast zu wenig Coins!");
                        player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                    break;

            }
        }

    }

}
