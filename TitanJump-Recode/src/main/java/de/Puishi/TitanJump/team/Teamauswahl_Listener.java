package de.Puishi.TitanJump.team;

import de.Puishi.TitanJump.TitanJump;
import de.Puishi.TitanJump.utils.Base64;
import de.Puishi.TitanJump.utils.Data;
import de.Puishi.TitanJump.utils.ItemBuilder;
import org.bukkit.Bukkit;
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
 *    Erstellt: 03.07.2020 / 09:22                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Teamauswahl_Listener implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §6Teamauswahl")) {
                    Inventory inventory = Bukkit.createInventory(null, 9, "§8» §6Teamauswahl");
                    player.openInventory(inventory);

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            inventory.setItem(0, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/86d35a963d5987894b6bc214e328b39cd2382426ff9c8e082b0b6a6e044d3a3")).setName("§8» §cRot").toItemStack());
                            inventory.setItem(1, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/3f3e406291174d24cdf0f953f8a174a82bb3489dce8f679a443ef1aae0169061")).setName("§8» §9Blau").toItemStack());
                            inventory.setItem(2, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/484684344ae098529fc941aa84e195bdca3748d69acfee2bac1332135edd98c")).setName("§8» §2Grün").toItemStack());
                            inventory.setItem(3, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/27bbd0b2911c96b5d87b2df76691a51b8b12c6fefd523146d8ac5ef1b8ee")).setName("§8» §eGelb").toItemStack());
                            inventory.setItem(4, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/ba94cb25de628ca359b2f6ea5a8868cbe26595eedb2bffb750967ad1ee1850")).setName("§8» §5Lila").toItemStack());
                            inventory.setItem(5, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/6becfb3879936b899e420bfcd3a74f8a1bf9dd54c58ec7fb9f81d9a5d988e")).setName("§8» §dPink").toItemStack());
                            inventory.setItem(6, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/f1af46febd45c0f4d81e8fa1b66b275d89e272b2ad55c978553a99c733e1ff")).setName("§8» §bHellblau").toItemStack());
                            inventory.setItem(7, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/d67470a0c18f6851e914353719e795877d29b3252f7e6bd4a1b865765bd74feb")).setName("§8» §aHellgrün").toItemStack());

                        }
                    }.runTaskLater(TitanJump.getInstance(), 1);
                }
            }
        }
    }

    @EventHandler
    public void onInvCLick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getName().equalsIgnoreCase("§8» §6Teamauswahl")) {
            if(event.getCurrentItem() != null) {
                if (event.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cRot")) {
                        if(TeamManager.rot.isEmpty()) {
                            TeamManager.rot.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.rot.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §9Blau")) {
                        if(TeamManager.blau.isEmpty()) {
                            TeamManager.blau.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.blau.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §2Grün")) {
                        if(TeamManager.grün.isEmpty()) {
                            TeamManager.grün.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.grün.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §eGelb")) {
                        if(TeamManager.gelb.isEmpty()) {
                            TeamManager.gelb.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §5Lila")) {
                        if(TeamManager.lila.isEmpty()) {
                            TeamManager.lila.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.lila.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §dPink")) {
                        if(TeamManager.pink.isEmpty()) {
                            TeamManager.pink.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.pink.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bHellblau")) {
                        if(TeamManager.hellblau.isEmpty()) {
                            TeamManager.hellblau.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.hellblau.remove(player.getUniqueId().toString());
                        }
                    } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aHellgrün")) {
                        if(TeamManager.hellgrün.isEmpty()) {
                            TeamManager.hellgrün.add(player.getUniqueId().toString());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 1, 1);
                            player.sendMessage(Data.getPrefix() + "Du bist dem Team beigetreten!");

                            TeamManager.rot.remove(player.getUniqueId().toString());
                            TeamManager.blau.remove(player.getUniqueId().toString());
                            TeamManager.gelb.remove(player.getUniqueId().toString());
                            TeamManager.grün.remove(player.getUniqueId().toString());
                            TeamManager.lila.remove(player.getUniqueId().toString());
                            TeamManager.pink.remove(player.getUniqueId().toString());
                            TeamManager.hellblau.remove(player.getUniqueId().toString());

                        } else {
                            player.sendMessage(Data.getPrefix() + "§cDas Team ist voll!");
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                            TeamManager.hellgrün.remove(player.getUniqueId().toString());
                        }
                    }
                }
            }
        }
    }

    public void randomTeam(Player player) {
        if (!(TeamManager.rot.contains(player.getUniqueId().toString())
            || TeamManager.blau.contains(player.getUniqueId().toString())
            || TeamManager.grün.contains(player.getUniqueId().toString())
            || TeamManager.gelb.contains(player.getUniqueId().toString())
            || TeamManager.lila.contains(player.getUniqueId().toString())
            || TeamManager.pink.contains(player.getUniqueId().toString())
            || TeamManager.hellgrün.contains(player.getUniqueId().toString())
            || TeamManager.hellblau.contains(player.getUniqueId().toString()))) {
            if(TeamManager.rot.isEmpty()) {
                TeamManager.rot.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if (TeamManager.blau.isEmpty()) {
                TeamManager.blau.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if (TeamManager.grün.isEmpty()) {
                TeamManager.grün.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if (TeamManager.gelb.isEmpty()) {
                TeamManager.gelb.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if(TeamManager.lila.isEmpty()) {
                TeamManager.lila.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if(TeamManager.pink.isEmpty()) {
                TeamManager.pink.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if(TeamManager.hellblau.isEmpty()) {
                TeamManager.hellblau.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else if(TeamManager.hellgrün.isEmpty()) {
                TeamManager.hellgrün.add(player.getUniqueId().toString());
                player.sendMessage(Data.getPrefix() + "Du wurdest in ein Team verschoben!");
            } else {
                player.sendMessage(Data.getPrefix() + "§cEs wurde kein passendes Team für dich gefunden!");
            }
        }
    }

}
