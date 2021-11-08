package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Main;
import de.Puishi.Lobby.utils.Base64;
import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.ItemBuilder;
import de.Puishi.Lobby.utils.SkullBuilder;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.*;
import java.util.ArrayList;

/********************************************************************************
 *    Urheberrechtshinweis                                                      *
 *    Copyright © Maxi D | Puishi. 2020                                                   *                                                                                    
 *    Erstellt: 19.05.2020 / 21:27                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.COMPASS) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Inventory inv = Bukkit.createInventory(null, 54, "§8» §6Navigator");
                    p.openInventory(inv);
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 5, 5);
                            inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                        }
                    }.runTaskLater(Main.getInstance(), 10);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 5, 5);
                            inv.setItem(12, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/4ceeb77d4d25724a9caf2c7cdf2d88399b1417c6b9ff5213659b653be4376e3")).setAmount(getGroupOnlineCount("Community")).setName("§8» §eCommunity").toItemStack());
                            inv.setItem(14, new ItemBuilder(Material.STICK).setName("§8» §cTTT").setAmount(getGroupOnlineCount("TTT")).toItemStack());
                            inv.setItem(19, new ItemBuilder(Material.BED).setName("§8» §3BedWars").setAmount(getGroupOnlineCount("BW-2x1")).toItemStack());
                            inv.setItem(25, new ItemBuilder(Material.GRASS).setName("§8» §2SkyWars").setAmount(getGroupOnlineCount("SW-8x1")).toItemStack());
                            inv.setItem(31, new ItemBuilder(Material.MAGMA_CREAM).setName("§8» §6Spawn").setAmount(getGroupOnlineCount("Lobby")).toItemStack());
                            inv.setItem(37, new ItemBuilder(Material.DIAMOND_BOOTS).setName("§8» §bTitanJump").setAmount(getGroupOnlineCount("TitanJump")).toItemStack());
                            inv.setItem(43, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§8» §9Survival").setAmount(getGroupOnlineCount("Survival")).toItemStack());
                        }
                    }.runTaskLater(Main.getInstance(), 20);
                }
            } else if(e.getItem().getType() == Material.ENDER_CHEST){
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    Inventory inv = Bukkit.createInventory(null, 54, "§8» §5Gadgets");
                    p.openInventory(inv);
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);

                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            p.playSound(p.getLocation(), Sound.NOTE_BASS, 5, 5);
                            inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                            inv.setItem(53, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 9).setName(" ").toItemStack());
                        }
                    }.runTaskLater(Main.getInstance(), 10);

                    new BukkitRunnable(){
                        @Override
                        public void run() {

                        }
                    }.runTaskLater(Main.getInstance(), 20);

                }
            } else if(e.getItem().getType() == Material.TNT){
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if(CloudAPI.getInstance().getServerInfo("Silent-Hub-1") != null) {
                        PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId()), "Silent-Hub-1");
                        p.sendMessage(Data.PRERIX + "Du bist nun auf der §cSilent-Hub§7.");
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
                    } else {
                        p.sendMessage(Data.PRERIX + "§cDie Silent-Hub ist derzeit offline.");
                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                    }
                }
            } else if(e.getItem().getType() == Material.NETHER_STAR){
                Inventory inv = Bukkit.createInventory(null, 9, "§8» §eLobby-Switcher");
                p.openInventory(inv);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if(CloudAPI.getInstance().getServerInfo("Lobby-1") != null) {
                            inv.setItem(0, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f")).setName("§8» §eLobby-1").toItemStack());
                        } else {
                            inv.setItem(0, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cOffline").toItemStack());
                        }

                        if(CloudAPI.getInstance().getServerInfo("Lobby-2") != null) {
                            inv.setItem(1, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f")).setName("§8» §eLobby-2").toItemStack());
                        } else {
                            inv.setItem(1, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cOffline").toItemStack());
                        }

                        if(CloudAPI.getInstance().getServerInfo("Lobby-3") != null) {
                            inv.setItem(2, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f")).setName("§8» §eLobby-3").toItemStack());
                        } else {
                            inv.setItem(2, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cOffline").toItemStack());
                        }

                        if(CloudAPI.getInstance().getServerInfo("Lobby-4") != null) {
                            inv.setItem(3, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/38be8abd66d09a58ce12d377544d726d25cad7e979e8c2481866be94d3b32f")).setName("§8» §eLobby-4").toItemStack());
                        } else {
                            inv.setItem(3, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025")).setName("§8» §cOffline").toItemStack());
                        }

                    }
                }.runTaskLater(Main.getInstance(), 1);
            } else if(e.getItem().getType() == Material.SKULL_ITEM){
                p.sendMessage(Data.PRERIX + "§cDieses Modul ist momentan deaktiviert!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            } else if(e.getItem().getType() == Material.NAME_TAG){
                p.sendMessage(Data.PRERIX + "§cDieses Modul ist momentan deaktiviert!");
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        }
    }

    @EventHandler
    public void onInteract1(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getItem() != null) {
            if (e.getItem().getType() == Material.BLAZE_ROD) {
                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    p.getInventory().setItem(1, new ItemBuilder(Material.STICK).setName("§8» §cAlle Spieler anzeigen").toItemStack());
                    Main.getInstance().getVisibleHandler().getHide().add(p);
                    Bukkit.getOnlinePlayers().forEach(p::hidePlayer);
                    p.sendMessage(Data.PRERIX + "Du siehst nun keine Spieler.");
                    p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                }
            } else if(e.getItem().getType() == Material.STICK){
                p.getInventory().setItem(1, new ItemBuilder(Material.BLAZE_ROD).setName("§8» §aAlle Spieler verstecken").toItemStack());
                Main.getInstance().getVisibleHandler().getHide().remove(p);
                Bukkit.getOnlinePlayers().forEach(p::showPlayer);
                p.sendMessage(Data.PRERIX + "Du siehst nun alle Spieler.");
                p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1, 1);
            }
        }
    }


    public static int getGroupOnlineCount(String group) {
        return CloudAPI.getInstance().getOnlineCount(group);
    }
}
