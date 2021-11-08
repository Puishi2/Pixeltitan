package de.vacebuild.trial.listener;

import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.api.utils.InventoryAPI;
import de.vacebuild.api.utils.ItemManager;
import de.vacebuild.trial.main.Trialsystem;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class WorldmanagerListener implements Listener {

    private Trialsystem plugin;
    private Inventory createInv;
    private Inventory defaultInv;
    private Inventory chooseTypeInv;

    public WorldmanagerListener(Trialsystem plugin) {
        this.plugin = plugin;

        ItemStack glass = new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b").setData(7).build();

        this.createInv = new InventoryAPI("§8» §bWorldmanager", 9 * 3).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 18).setItem(glass, 19).setItem(glass, 20).setItem(glass, 21).setItem(glass, 22).setItem(glass, 23)
                .setItem(glass, 24).setItem(glass, 25).setItem(glass, 26)
                .setItem(new ItemManager(Material.INK_SACK).setDisplayName("§8» §bWelt erstellen").setData(10).build(), 13)
                .build();

        this.defaultInv = new InventoryAPI("§8» §bWorldmanager", 9 * 3).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 18).setItem(glass, 19).setItem(glass, 20).setItem(glass, 21).setItem(glass, 22).setItem(glass, 23)
                .setItem(glass, 24).setItem(glass, 25).setItem(glass, 26)
                .setItem(new ItemManager(Material.INK_SACK).setDisplayName("§8» §bTeleportieren").setData(10).build(), 11)
                .setItem(new ItemManager(Material.INK_SACK).setDisplayName("§8» §bWelt abgeben").setData(1).build(), 13)
                .setItem(new ItemManager(Material.INK_SACK).setDisplayName("§8» §bWelt zurücksetzen").setData(11).build(), 15)
                .build();

        this.chooseTypeInv = new InventoryAPI("§8» §bWorldmanager", 9 * 3).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 18).setItem(glass, 19).setItem(glass, 20).setItem(glass, 21).setItem(glass, 22).setItem(glass, 23)
                .setItem(glass, 24).setItem(glass, 25).setItem(glass, 26)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bFlach",
                        "http://textures.minecraft.net/texture/c95d37993e594082678472bf9d86823413c250d4332a2c7d8c52de4976b362"), 11)
                .setItem(VaceAPI.getApi().getSkullAPI().getSkull("§8» §bLeer",
                        "http://textures.minecraft.net/texture/d52efad1be84e0889bb2a581a5e665aa070622260fbf6c1eb8d931a1267a54ba"), 15).build();
    }

    @EventHandler
    public void handleInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();

        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();

            if (armorStand.getCustomName().equalsIgnoreCase("§8» §bWorldmanager")) {
                if (plugin.getMySQL().hasWorld(player.getUniqueId())) {
                    player.openInventory(this.defaultInv);
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5F, 0.5F);
                } else {
                    player.openInventory(this.createInv);
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5F, 0.5F);
                }
            }
        }
    }

    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null && event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWorldmanager")) {
            event.setCancelled(true);

            if (event.getCurrentItem().getType() == Material.INK_SACK) {
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt erstellen")) {
                    if (!plugin.getMySQL().hasWorld(player.getUniqueId())) {
                        player.openInventory(this.chooseTypeInv);
                        player.sendMessage(plugin.getData().getPrefix() + "§7Bitte wähle einen §bWelttpyen §7aus§8.");
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast bereits eine §bWelt§8.");
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bTeleportieren")) {
                    player.closeInventory();

                    if (plugin.getMySQL().hasWorld(player.getUniqueId())) {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du wirst auf deine §bWelt §7teleportiert§8.");

                        if (plugin.getServer().getWorld("Worlds//" + player.getUniqueId().toString()) != null) {
                            player.teleport(plugin.getServer().getWorld("Worlds//" + player.getUniqueId().toString()).getSpawnLocation());
                        } else {
                            plugin.getServer().createWorld(new WorldCreator("Worlds//" + player.getUniqueId().toString()));

                            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                                    player.teleport(plugin.getServer().getWorld("Worlds//" + player.getUniqueId().toString()).getSpawnLocation()), 5L);
                        }
                    } else {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast noch keine §bWelt§8.");
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt abgeben")) {

                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt zurücksetzen")) {

                }
            } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                player.closeInventory();

                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFlach")) {
                    createWorld(player, "Flat");
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bLeer")) {
                    createWorld(player, "Empty");
                }
            }
        }
    }

    private void createWorld(Player player, String path) {
        try {
            FileUtils.copyDirectory(new File("WorldTemplates//" + path), new File("Worlds//" + player.getUniqueId().toString()));

            String topic = plugin.getTopicManager().getRandomTopic();

            plugin.getServer().createWorld(new WorldCreator("Worlds//" + player.getUniqueId().toString()));
            plugin.getMySQL().setHasWorld(player.getUniqueId(), true);
            plugin.getMySQL().setTopic(player.getUniqueId(), topic);

            player.sendMessage(plugin.getData().getPrefix() + "§7Deine §bWelt §7wurde erfolgreich erstellt§8.");
            player.sendMessage(plugin.getData().getPrefix() + "§7Dein Bauthema ist§8: §b" + topic);

            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, () ->
                    player.teleport(plugin.getServer().getWorld("Worlds//" + player.getUniqueId().toString()).getSpawnLocation()), 5L);
        } catch (Exception e) {
            player.sendMessage("§cFehler");
        }
    }
}
