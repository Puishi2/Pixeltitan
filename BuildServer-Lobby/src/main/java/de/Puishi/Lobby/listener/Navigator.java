package de.Puishi.Lobby.listener;

import de.Puishi.Lobby.Lobby;
import de.Puishi.Lobby.utils.Data;
import de.Puishi.Lobby.utils.ItemBuilder;
import de.Puishi.Lobby.utils.LocationManager;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
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
 *    Erstellt: 08.07.2020 / 17:54                                               *
 *                                                                              *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.          *
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,  *
 *    bei Maxi D. Alle Rechte vorbehalten.                                 *
 *                                                                              *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,       *
 *    öffentlichen Zugänglichmachung oder andere Nutzung                        *
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Maxi D.      *
 *******************************************************************************/

public class Navigator implements Listener {

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getItem() != null) {
            if(event.getItem().getItemMeta().getDisplayName() != null) {
                if(event.getItem().getType() == Material.CLAY_BALL) {
                    Inventory inventory = Bukkit.createInventory(null, 9*6, "§8» §3Navigator");
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

                            inventory.setItem(12, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§8» §3Bauserver").toItemStack());
                            inventory.setItem(14, new ItemBuilder(Material.IRON_PICKAXE).setName("§8» §3Vorbauen").toItemStack());
                            inventory.setItem(28, new ItemBuilder(Material.BOOK).setName("§8» §3Devserver").toItemStack());
                            inventory.setItem(34, new ItemBuilder(Material.BOOK_AND_QUILL).setName("§8» §3Konzeptserver").toItemStack());
                            inventory.setItem(40, new ItemBuilder(Material.FLOWER_POT_ITEM).setName("§8» §3Spawn").toItemStack());

                        }
                    }.runTaskLater(Lobby.getInstance(), 1);

                }
            }
        }
    }

    @EventHandler
    public void onInvClick(final InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory() != null) {
            if(event.getCurrentItem().getType() == Material.IRON_PICKAXE) {
                PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()), "Vorbau-1");
                player.sendMessage(Data.getPrefix() + "Du wurdest verbunden!");
            } else if(event.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
                PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()), "BauServer-1");
                player.sendMessage(Data.getPrefix() + "Du wurdest verbunden!");
            } else if(event.getCurrentItem().getType() == Material.BOOK) {
                PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()), "DevServer-1");
                player.sendMessage(Data.getPrefix() + "Du wurdest verbunden!");
            } else if(event.getCurrentItem().getType() == Material.FLOWER_POT_ITEM) {
                player.teleport(new LocationManager().getLocation("Spawn"));
                player.sendMessage(Data.getPrefix() + "Du wurdest teleportiert!");
            } else if(event.getCurrentItem().getType() == Material.BOOK_AND_QUILL) {
                PlayerExecutorBridge.INSTANCE.sendPlayer(CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId()), "Konzept-1");
                player.sendMessage(Data.getPrefix() + "Du wurdest verbunden!");
            }
        }
    }
}
