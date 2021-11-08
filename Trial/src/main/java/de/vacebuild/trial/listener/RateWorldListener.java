package de.vacebuild.trial.listener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.OfflinePlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionGroup;
import de.vacebuild.api.main.VaceAPI;
import de.vacebuild.api.utils.InventoryAPI;
import de.vacebuild.api.utils.ItemManager;
import de.vacebuild.trial.main.Trialsystem;
import de.vacebuild.trial.utils.EditWorldEvent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class RateWorldListener implements Listener {

    private Trialsystem plugin;
    private Inventory defaultInv;
    private Inventory denyInv;
    private HashMap<Player, String> reasons = new HashMap<>();

    public RateWorldListener(Trialsystem plugin) {
        this.plugin = plugin;

        ItemStack glass = new ItemManager(Material.STAINED_GLASS_PANE).setDisplayName("§b").setData(7).build();

        this.defaultInv = new InventoryAPI("§8» §bWelt bewerten", 9 * 3).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 18).setItem(glass, 19).setItem(glass, 20).setItem(glass, 21).setItem(glass, 22).setItem(glass, 23)
                .setItem(glass, 24).setItem(glass, 25).setItem(glass, 26)
                .setItem(new ItemManager(Material.STAINED_CLAY).setDisplayName("§8» §bWelt annehmen").setData(5).build(), 11)
                .setItem(new ItemManager(Material.STAINED_CLAY).setDisplayName("§8» §bWelt ablehnen").setData(14).build(), 15).build();

        this.denyInv = new InventoryAPI("§8» §bWelt ablehnen", 9 * 4).setItem(glass, 0).setItem(glass, 1).setItem(glass, 2)
                .setItem(glass, 3).setItem(glass, 4).setItem(glass, 5).setItem(glass, 6).setItem(glass, 7).setItem(glass, 8)
                .setItem(glass, 27).setItem(glass, 28).setItem(glass, 29).setItem(glass, 30).setItem(glass, 32)
                .setItem(glass, 33).setItem(glass, 34).setItem(glass, 35)
                .setItem(new ItemManager(Material.GRASS).setDisplayName("§8» §bTerrain").build(), 10)
                .setItem(new ItemManager(Material.WOOL).setDisplayName("§8» §bColoring").build(), 11)
                .setItem(new ItemManager(Material.LEAVES).setDisplayName("§8» §bUnstimmige Atmosphäre").build(), 12)
                .setItem(new ItemManager(Material.RED_ROSE).setDisplayName("§8» §bVegetation").build(), 13)
                .setItem(new ItemManager(Material.GLASS).setDisplayName("§8» §bZu leer").build(), 14)
                .setItem(new ItemManager(Material.HARD_CLAY).setDisplayName("§8» §bUnfertig").build(), 15)
                .setItem(new ItemManager(Material.DIRT).setDisplayName("§8» §bZu monoton").build(), 16)
                .setItem(new ItemManager(Material.WOOD).setDisplayName("§8» §bFassaden").build(), 19)
                .setItem(new ItemManager(Material.ENDER_PORTAL_FRAME).setDisplayName("§8» §bStruktur").build(), 20)
                .setItem(new ItemManager(Material.CHEST).setDisplayName("§8» §bThema verfehlt").build(), 21)
                .setItem(new ItemManager(Material.PAPER).setDisplayName("§8» §bAbsenden").build(), 31)
                .build();
    }

    @EventHandler
    public void handleInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getItemInHand().getType() == Material.DIAMOND &&
                    player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt bewerten")) {
                if (player.hasPermission("vacebuild.srstaff")) {
                    if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                            .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
                        UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                        if (plugin.getMySQL().isPlayerExists(uuid)) {
                            if (plugin.getMySQL().getStatus(uuid) == 1) {
                                player.openInventory(this.defaultInv);
                                player.playSound(player.getLocation(), Sound.CHEST_OPEN, 0.5F, 0.5F);
                            } else {
                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                                player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7wurde nicht zur Bewertung abgegeben§8.§8.");
                            }
                        } else {
                            player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                        }
                    } else {
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                        player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                    }
                }
            }
        }
    }

    @Deprecated
    @EventHandler
    public void handleClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() != null) {
            if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt bewerten")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() == Material.STAINED_CLAY) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt annehmen")) {
                        plugin.getInventoryManager().openConfirmInv(player, "Welt annehmen", Sound.ITEM_PICKUP);
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bWelt ablehnen")) {
                        this.reasons.put(player, "");

                        Inventory inventory = new InventoryAPI("§8» §bWelt ablehnen", 9 * 4).build();

                        inventory.setContents(this.denyInv.getContents());

                        player.openInventory(inventory);
                        player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt annehmen")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() == Material.STAINED_CLAY) {
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bFortfahren")) {
                        if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                                .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
                            UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                            if (plugin.getMySQL().isPlayerExists(uuid)) {
                                if (plugin.getMySQL().getStatus(uuid) == 1) {
                                    plugin.getMySQL().setStatus(uuid, 2);

                                    player.sendMessage(plugin.getData().getPrefix() + "§7Du hast die Welt von §b" +
                                            VaceAPI.getApi().getPlayerAPI().getName(uuid) + " §7angenommen§8.");

                                    for (Player all : plugin.getServer().getOnlinePlayers()) {
                                        plugin.getScoreboardManager().updateScoreboard(all);
                                    }

                                    send(player, uuid, "AcceptWorld", false, "");

                                    plugin.getMySQL().finishedWorlds.remove(uuid.toString());

                                    plugin.getServer().getPluginManager().callEvent(new EditWorldEvent());
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7wurde nicht zur Bewertung abgegeben§8.§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                        }
                    } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAbbrechen")) {
                        player.sendMessage(plugin.getData().getPrefix() + "§7Der §bVorgang §7wurde abgebrochen§8.");
                    }
                }
            } else if (event.getClickedInventory().getName().equalsIgnoreCase("§8» §bWelt ablehnen")) {
                event.setCancelled(true);

                if (event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 0.5F, 0.5F);

                    if (event.getCurrentItem().getType() == Material.PAPER &&
                            event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §bAbsenden")) {
                        if (!this.reasons.get(player).equalsIgnoreCase("")) {
                            player.closeInventory();

                            if (player.getWorld().getName().contains("Worlds//") && VaceAPI.getApi().getPlayerAPI()
                                    .isPlayerExists(UUID.fromString(player.getWorld().getName().replace("Worlds//", "")))) {
                                UUID uuid = UUID.fromString(player.getWorld().getName().replace("Worlds//", ""));

                                if (plugin.getMySQL().isPlayerExists(uuid)) {
                                    if (plugin.getMySQL().getStatus(uuid) == 1) {
                                        plugin.getMySQL().setStatus(uuid, 3);
                                        plugin.getMySQL().setReason(uuid, this.reasons.get(player));

                                        player.sendMessage(plugin.getData().getPrefix() + "§7Du hast die Welt von §b" +
                                                VaceAPI.getApi().getPlayerAPI().getName(uuid) + " §7abgelehnt§8.");

                                        for (Player all : plugin.getServer().getOnlinePlayers()) {
                                            plugin.getScoreboardManager().updateScoreboard(all);
                                        }

                                        send(player, uuid, "DenyWorld", true, this.reasons.get(player));

                                        plugin.getMySQL().finishedWorlds.remove(uuid.toString());

                                        plugin.getServer().getPluginManager().callEvent(new EditWorldEvent());
                                    } else {
                                        player.sendMessage(plugin.getData().getPrefix() + "§7Diese §bWelt §7wurde nicht zur Bewertung abgegeben§8.§8.");
                                    }
                                } else {
                                    player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                                }
                            } else {
                                player.sendMessage(plugin.getData().getPrefix() + "§7Du bist in keiner §bWelt§8.");
                            }
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Bitte gib mindestens §beinen Grund §7an§8.");
                        }
                    } else {
                        String reason = event.getCurrentItem().getItemMeta().getDisplayName().replace("§8» §b", "");

                        if (!this.reasons.get(player).contains(reason)) {
                            this.reasons.put(player, this.reasons.get(player) + reason + ";");

                            player.getOpenInventory().getTopInventory().setItem(event.getSlot(), new ItemManager(player.
                                    getOpenInventory().getTopInventory().getItem(event.getSlot()))
                                    .addEnchantment(Enchantment.DAMAGE_ALL, 1).hideFlags().build());

                            player.updateInventory();
                        } else {
                            player.sendMessage(plugin.getData().getPrefix() + "§7Du hast diesen §bGrund §7bereits angegeben§8.");
                        }
                    }
                }
            }

        }
    }

    private void send(Player player, UUID uuid, String channel, boolean deny, String reasons) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        if (deny) {
            out.writeUTF(uuid.toString() + ";" + reasons);
        } else {
            out.writeUTF(uuid.toString());
        }

        player.sendPluginMessage(plugin, channel, out.toByteArray());
    }

}
